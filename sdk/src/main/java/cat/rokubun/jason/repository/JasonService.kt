package cat.rokubun.jason.repository

import android.content.Context
import android.webkit.MimeTypeMap
import cat.rokubun.jason.*
import cat.rokubun.jason.Location
import cat.rokubun.jason.User
import cat.rokubun.jason.repository.remote.ApiService
import cat.rokubun.jason.repository.remote.dto.SubmitProcessResult
import cat.rokubun.jason.repository.remote.dto.UserLoginResult
import cat.rokubun.jason.utils.Hasher
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
/**
 * This class is responsible for managing the requests made to the JASON API
 **/

class JasonService {

    private var apiService: ApiService
    private var serviceFactory: ServiceFactory? = null
    private var token: String? = null
    private var logRequestJob: Job? = null
    private val MAX_LOG_REQUEST_RETRIES: Int = 5 * 60
    private val DELAY_LOG_REQUEST: Long = 1000L


    /**
     * @constructor instantiate ServiceFactory and create an ApiService
     */
    constructor(context: Context) {
        serviceFactory = ServiceFactory(context)
        apiService = serviceFactory!!.getService(
            JasonClient.URL,
            JasonClient.API_KEY
        )?.create(ApiService::class.java)!!
    }

    /**
     * Insert user token for every request
     */
    fun setToken (token: String?) {
        this.token = token
    }

    /**
     *
     * Perform login in request and emit a Single<User>. Errors are provided on the OnError method
     * @param user user's email
     * @param password user's password
     * @return if login is correct Single<User>
     * @throws ResponseCodeEum ERROR if something went wrong, FORBIDDEN if login is unsuccessful
     */
    fun login(user: String?, password: String?): Single<User> {
        return Single.create { emitter ->
            apiService.userlogin(user, Hasher.hash(password))
                .enqueue((object : Callback<UserLoginResult> {
                    override fun onFailure(call: Call<UserLoginResult>, t: Throwable) {
                        emitter.onError(Throwable(ResponseCodeEum.ERROR.description))
                    }

                    override fun onResponse(
                        call: Call<UserLoginResult>,
                        response: Response<UserLoginResult>
                    ) {
                        when (response.code()) {
                            200 -> {
                                val userResponse = User(
                                    response.body()?.name,
                                    response.body()?.surname,
                                    response.body()!!.token,
                                    response.body()?.email,
                                    response.body()?.id,
                                    response.body()?.type
                                )
                                token = response.body()!!.token
                                emitter.onSuccess(userResponse)
                            }
                            401 -> {
                                emitter.onError(Throwable(ResponseCodeEum.FORBIDDEN.description))
                            }
                        }
                    }
                }))
        }
    }

    /**
     * Logout user from Jason by removing token
     */
    fun logout() {
        token = null
    }

    /**
     * Sent file to JASON in order process it, the responses are parsed and emitted
     *
     * @param type of processing to be performed
     * @param roverFile rover file to process
     * @param baseFile base file to process
     * @param location of the base station
     * @return Single<SubmitProcessResult>
     */
    //TODO label
    fun submitProcess(label: String, type: String, roverFile: File, baseFile: File? = null, location: Location? = null): Single<SubmitProcessResult> {
        val requestFile = roverFile.asRequestBody(getMimeType(roverFile.name)?.toMediaTypeOrNull())
        val roverPartFile = MultipartBody.Part.createFormData("rover_file", roverFile.name, requestFile)
        val secretToken = token!!.toRequestBody()
        val typePart = type.toRequestBody()
        val labelPart = label.toRequestBody()

        if (baseFile == null) {
           return Single.create{ emiter ->
               apiService.submitProcess(labelPart, secretToken, typePart, roverPartFile)
                   .enqueue(submitProcessCallback(emiter))
           }
        } else {
            val requestBaseFile =
                roverFile.asRequestBody(getMimeType(baseFile.name)?.toMediaTypeOrNull())
            val basePartFile =
                MultipartBody.Part.createFormData("base_file", roverFile.name, requestBaseFile)

            val requestLocation = location?.toQueryString()?.toRequestBody()

            return Single.create { emitter ->
                apiService.submitProcess(labelPart, secretToken, typePart, roverPartFile, basePartFile,requestLocation)
                    .enqueue(submitProcessCallback(emitter))
            }
        }
    }

    /**
     * Callback to submit process
     */
    private fun submitProcessCallback(emitter: SingleEmitter<SubmitProcessResult>): Callback<SubmitProcessResult> {
        return object : Callback<SubmitProcessResult> {
            override fun onFailure(call: Call<SubmitProcessResult>, t: Throwable) {
                emitter.onError(Throwable(ResponseCodeEum.ERROR.description))
            }

            override fun onResponse(
                call: Call<SubmitProcessResult>,
                response: Response<SubmitProcessResult>
            ) {
                emitter.onSuccess(SubmitProcessResult(response.body()?.message, response.body()?.id))

            }
        }
    }

    /**
     * Requests periodically the status of the process id passed as parameter. If the state is "RUNNING" ,
     * requests are made every second. When the status is FINISHED or and ERROR is returned, the requests
     * are stopped
     *
     * @param processId process ID
     * @param maxTimeoutMillis time to wait for a server respond
     * @return [Observable]<[ProcessStatus]>
     */

    fun getProcessStatus(processId: Int, maxTimeoutMillis: Long):Observable<ProcessStatus> {
        //this.logListener = logListener
        var processLogList:List<ProcessLog> ?= null
        var processStatus : ProcessStatus
        return Observable.create { emiter ->
            logRequestJob = CoroutineScope(Dispatchers.IO).launch {
                repeat(MAX_LOG_REQUEST_RETRIES) { i ->
                    try {
                        val response = getProcessInformation(processId)
                        when (response?.body()?.process?.status) {
                            "PENDING" -> delay(DELAY_LOG_REQUEST)
                            "RUNNING" -> {
                                processLogList = ProcessResultConverter.getProcessLogFromStatusResult(response.body()!!)
                                processStatus = ProcessStatus(processLogList!!)
                                emiter.onNext(processStatus)
                                delay(DELAY_LOG_REQUEST)
                            }
                            "FINISHED" -> {
                                val processResult: ProcessResult? =
                                   ProcessResult(response.body()?.results!!)
                                processLogList = ProcessResultConverter.getProcessLogFromStatusResult(response.body()!!)
                                processStatus = ProcessStatus(processLogList!!, processResult!!)
                                emiter.onNext(processStatus)
                                logRequestJob?.join()
                                emiter.onComplete()
                            }
                            else -> {
                                val processError: ProcessError? = ProcessError(response?.code()!!)
                                logRequestJob?.cancelAndJoin()
                                emiter.onError(Throwable(ResponseCodeEum.ERROR.description))
                            }
                        }
                    } catch (e: Throwable) {
                        logRequestJob?.cancelAndJoin()
                        emiter.onError(Throwable(ResponseCodeEum.ERROR.description))
                    }

                }
            }
        }

    }

    private suspend fun getProcessInformation(processId: Int) =
        apiService.getProcessInformation(processId, this.token!!)

    /**
     * Return Minetype of URL
     */
    private fun getMimeType(url: String?): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }
}

    enum class ResponseCodeEum(val code: Int, val description: String) {
        OK(200, "Success"),
        FORBIDDEN(401, "User or password incorrect"),
        ERROR(500, "Service is no available")
    }