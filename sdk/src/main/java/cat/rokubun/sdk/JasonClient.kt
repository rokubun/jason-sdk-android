package cat.rokubun.sdk


import android.content.Context
import android.location.Location
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.lifecycle.MutableLiveData
import cat.rokubun.sdk.domain.User
import cat.rokubun.sdk.repository.ServiceFactory

import cat.rokubun.sdk.repository.remote.ApiService
import cat.rokubun.sdk.repository.remote.dto.SubmitProcessResult
import cat.rokubun.sdk.repository.remote.dto.UserLoginResult
import cat.rokubun.sdk.utils.Hasher
import io.reactivex.Single
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.net.SocketTimeoutException
import java.util.*


class JasonClient {
    private val DELAY_LOG_REQUEST: Long = 1000L
    private val MAX_LOG_REQUEST_RETRIES: Int = 5 * 60
    private val MAX_LOG_REQUEST_TIMEOUT_MS: Long = MAX_LOG_REQUEST_RETRIES * DELAY_LOG_REQUEST

    //TODO EXTRACT TO STRING.xml
    private var URL: String = "http://api-argonaut.rokubun.cat:80/api/"
    private var API_KEY = "ARGONAUT.BOF.LQIGHJEYRT754651059DJ5UFM59MS93M"
    private var retrofitInstance: ApiService? = null
    private var user: User? = null
    private var logListener: LogListener? = null
    private var logRequestJob: Job? = null
    private var serviceFactory: ServiceFactory? = null


    constructor(context: Context) {
        serviceFactory = ServiceFactory(context)
        retrofitInstance = serviceFactory!!.getClient(URL, API_KEY)?.create(ApiService::class.java)!!
    }
    fun login(email: String?, password: String?): Single<User> {
        val map : MutableMap<Int, String> = mutableMapOf<Int, String>()
        return Single.create { emitter ->
            retrofitInstance?.userlogin(email, Hasher.hash(password))
                ?.enqueue((object : Callback<UserLoginResult> {
                    override fun onFailure(call: Call<UserLoginResult>, t: Throwable) {
                        emitter.onError(Throwable(ResponseCodeEum.ERROR.description))
                    }
                    override fun onResponse(
                        call: Call<UserLoginResult>,
                        response: Response<UserLoginResult>
                    ) {
                        when (response.code()) {
                            200 -> {
                                user = User(
                                    response.body()?.name,
                                    response.body()?.surname,
                                    response.body()!!.token,
                                    response.body()?.email,
                                    response.body()?.id
                                )
                                emitter.onSuccess(user!!)
                            }
                            401 -> {
                                emitter.onError(Throwable(ResponseCodeEum.FORBIDDEN.description))
                            }
                        }
                    }
                }))
        }
    }

    fun submitProcess(type: String, roverFile: File) {
        if (user?.secretToken!!.isNotEmpty() && API_KEY.isNotEmpty()) {
            var location: Location? = null

            val requestFile =
                roverFile.asRequestBody(getMimeType(roverFile.name)?.toMediaTypeOrNull())
            val partFile =
                MultipartBody.Part.createFormData("rover_file", roverFile.name, requestFile)
            val secretToken = user?.secretToken!!.toRequestBody()
            val typePart = type.toRequestBody()

            retrofitInstance?.submitProcess(secretToken, typePart, partFile)
                ?.enqueue((object : Callback<SubmitProcessResult> {

                    override fun onFailure(call: Call<SubmitProcessResult>, t: Throwable) {
                        Log.e("Submit: ", "error:", t.cause)
                    }

                    override fun onResponse(
                        call: Call<SubmitProcessResult>,
                        response: Response<SubmitProcessResult>
                    ) {
                        Log.d("Response:", response.message())
                        SubmitProcessResult(response.body()?.id, response.body()?.message)
                    }
                }))
        }
    }

    fun submitProcess(type: String, roverFile: File, baseFile: File, location: String) {

        val requestRoverFile =
            roverFile.asRequestBody(getMimeType(roverFile.name)?.toMediaTypeOrNull())
        val roverPartFile =
            MultipartBody.Part.createFormData("rover_file", roverFile.name, requestRoverFile)

        val requestBaseFile =
            roverFile.asRequestBody(getMimeType(baseFile.name)?.toMediaTypeOrNull())
        val basePartFile =
            MultipartBody.Part.createFormData("base_file", roverFile.name, requestBaseFile)

        val requestLocation = location.toRequestBody()
        val secretToken = user?.secretToken!!.toRequestBody()
        val typePart = type.toRequestBody()

        retrofitInstance?.submitProcess(
            secretToken,
            typePart,
            roverPartFile,
            basePartFile,
            requestLocation
        )
            ?.enqueue((object : Callback<SubmitProcessResult> {

                override fun onFailure(call: Call<SubmitProcessResult>, t: Throwable) {
                    Log.e("Submit: ", "error:", t.cause)
                }
                override fun onResponse(
                    call: Call<SubmitProcessResult>,
                    response: Response<SubmitProcessResult>
                ) {
                    Log.d("Response:", response.message())
                    SubmitProcessResult(response.body()?.id, response.body()?.message)
                }
            }))
    }

    fun registerLogListener(
        logListener: LogListener,
        idProcess: Int,
        timeOutMillis: Long = MAX_LOG_REQUEST_TIMEOUT_MS
    ) {
        if (user?.secretToken!!.isNotEmpty() && API_KEY.isNotEmpty()) {
        this.logListener = logListener

        logRequestJob = CoroutineScope(Dispatchers.IO).launch {
            repeat(MAX_LOG_REQUEST_RETRIES) { i ->
                try {
                    val response = getProccessInformation(idProcess)
                    when (response?.body()?.process?.status) {
                            "RUNNING" -> {
                                val processLogList: List<ProcessLog>? =
                                    ProcessResultConverter.getProcessLogFromStatusResult(
                                        response.body()!!
                                    )
                                logListener.onLogReceived(processLogList!!)
                                delay(DELAY_LOG_REQUEST)
                            }
                            "FINISHED" -> {
                                val processResult: ProcessResult? =
                                    ProcessResult(response.body()?.results!!)
                                logListener.onFinish(processResult!!)
                                logRequestJob?.join()
                            }
                            else -> {
                                val processError: ProcessError? = ProcessError(response?.code()!!)
                                Log.e("error", processError?.getErrorMessage(), Throwable())
                                Log.e("error", response.code().toString())
                                unregisterLogListener()
                            }
                        }
                    } catch (e: Throwable) {
                    Log.e("Throwable", "", e)
                    unregisterLogListener()
                    }

                }
            }
        }
    }

    suspend fun unregisterLogListener() {
        logRequestJob?.cancelAndJoin()
        logListener = null
    }

    private suspend fun getProccessInformation(processId: Int) =

        retrofitInstance?.getProcessInformation(processId, user?.secretToken!!)

    private fun getMimeType(url: String?): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }

    private fun toQueryString(location: Location): String {
        return String.format(
            Locale.ENGLISH, "%.08f,%.08f,%.08f",
            location.latitude, location.longitude, location.altitude
        );
    }

    enum class ResponseCodeEum(val code: Int, val description: String) {
        OK(200, "Login susccess"),
        FORBIDDEN(401, "User or password incorrect"),
        ERROR(500, "Service is no available")

    }
}







