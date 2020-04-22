package cat.rokubun.sdk


import android.content.Context
import cat.rokubun.sdk.domain.Location
import cat.rokubun.sdk.repository.JasonService
import cat.rokubun.sdk.domain.User
import cat.rokubun.sdk.repository.ServiceFactory

import cat.rokubun.sdk.repository.remote.ApiService
import cat.rokubun.sdk.repository.remote.dto.SubmitProcessResult
import cat.rokubun.sdk.utils.SingletonHolder
import io.reactivex.Observable
import io.reactivex.Single
import java.io.File

class JasonClient private constructor(context: Context) {
    private var jasonService: JasonService? = null
    private val DELAY_LOG_REQUEST: Long = 1000L
    private val MAX_LOG_REQUEST_RETRIES: Int = 5 * 60
    private val MAX_LOG_REQUEST_TIMEOUT_MS: Long = MAX_LOG_REQUEST_RETRIES * DELAY_LOG_REQUEST
    private var retrofitInstance: ApiService? = null
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

    fun logout(): Unit? {
        return jasonService?.logout()
    }

    fun submitProcess(type: String, roverFile: File): Single<SubmitProcessResult> {
        return jasonService!!.submitProcess(type, roverFile)
    }

    fun submitProcess(type: String, roverFile: File, baseFile: File, location: Location) :Single<SubmitProcessResult> {
        return jasonService!!.submitProcess(type, roverFile, baseFile, location)
    }
    //FIXME NAMING
    fun registerLogListener(idProcess: Int, timeOutMillis: Long = MAX_LOG_REQUEST_TIMEOUT_MS): Observable<JasonProcess> {
        return jasonService!!.registerLogListener(idProcess, timeOutMillis)
    }

    suspend fun unregisterLogListener() {
        jasonService!!.unregisterLogListener()
    }


}







