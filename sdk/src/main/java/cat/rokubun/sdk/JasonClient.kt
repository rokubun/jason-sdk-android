package cat.rokubun.sdk


import android.content.Context
import android.util.Log
import android.webkit.MimeTypeMap
import cat.rokubun.sdk.domain.Location
import cat.rokubun.sdk.repository.JasonService
import cat.rokubun.sdk.domain.User
import cat.rokubun.sdk.repository.ServiceFactory

import cat.rokubun.sdk.repository.remote.ApiService
import cat.rokubun.sdk.repository.remote.dto.SubmitProcessResult
import cat.rokubun.sdk.utils.SingletonHolder
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

class JasonClient private constructor(context: Context) {
    private var jasonService: JasonService? = null
    private val DELAY_LOG_REQUEST: Long = 1000L
    private val MAX_LOG_REQUEST_RETRIES: Int = 5 * 60
    private val MAX_LOG_REQUEST_TIMEOUT_MS: Long = MAX_LOG_REQUEST_RETRIES * DELAY_LOG_REQUEST
    private var retrofitInstance: ApiService? = null
    private var user: User? = null
    private var logListener: LogListener? = null
    private var logRequestJob: Job? = null
    private var serviceFactory: ServiceFactory? = null

    init {
        // Init using context argument
        jasonService = JasonService(context)
        serviceFactory = ServiceFactory(context)
        retrofitInstance = serviceFactory!!.getService(Companion.URL, Companion.API_KEY)?.create(ApiService::class.java)!!

    }

    companion object : SingletonHolder<JasonClient, Context>(::JasonClient) {
        val URL: String = "http://api-argonaut.rokubun.cat:80/api/"
        val API_KEY: String = "ARGONAUT.BOF.LQIGHJEYRT754651059DJ5UFM59MS93M"
    }

    fun login(email: String?, password: String?): Single<User>? {
        return jasonService?.login(email, password)
    }

    fun submitProcess(type: String, roverFile: File) {
        jasonService?.submitProcess(type, roverFile)
    }

    fun submitProcess(type: String, roverFile: File, baseFile: File, location: Location) {
        jasonService?.submitProcess(type, roverFile, baseFile, location)
    }

    fun registerLogListener(
        logListener: LogListener,
        idProcess: Int,
        timeOutMillis: Long = MAX_LOG_REQUEST_TIMEOUT_MS
    ) {
        if (user?.secretToken!!.isNotEmpty() && Companion.API_KEY.isNotEmpty()) {
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

}







