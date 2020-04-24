package cat.rokubun.sdk


import android.content.Context
import cat.rokubun.sdk.domain.Location
import cat.rokubun.sdk.repository.JasonService
import cat.rokubun.sdk.domain.User
import cat.rokubun.sdk.repository.ServiceFactory

import cat.rokubun.sdk.repository.remote.ApiService
import cat.rokubun.sdk.repository.JasonService
import cat.rokubun.sdk.repository.remote.dto.SubmitProcessResult
import cat.rokubun.sdk.utils.SingletonHolder
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import java.io.File

/**
 * JasonClient class is responsible for making calls to JASON related services
 * @see Single
 * @see Observable
 * @see MultipartBody
 * @constructor Context provides Android resources
 */
class JasonClient private constructor(context: Context) {
    private var jasonService: JasonService? = null
    private val DELAY_LOG_REQUEST: Long = 1000L
    private val MAX_LOG_REQUEST_RETRIES: Int = 5 * 60
    private val MAX_LOG_REQUEST_TIMEOUT_MS: Long = MAX_LOG_REQUEST_RETRIES * DELAY_LOG_REQUEST

    init {
        // Init using context argument
        jasonService = JasonService(context)
    }

    /**
     * Create a singleton JasonClient object
     * @param JasonClient
     * @param Context
     * @property URL base URL for request
     * @property API_KEY Api key for request
     */

    companion object : SingletonHolder<JasonClient, Context>(::JasonClient) {
        val URL: String = "http://api-argonaut.rokubun.cat:80/api/"
        val API_KEY: String = "ARGONAUT.BOF.LQIGHJEYRT754651059DJ5UFM59MS93M"
    }

    /**
     * Make the Login process to Jason and return a Single<User> object.
     * @param email user's email
     * @param password  user's password
     * @return Single<[User]>
     */
    fun login(email: String?, password: String?): Single<User>? {
        return jasonService?.login(email, password)
    }

    /**
     * Set token in JasonService.
     * @param token user's token
     */
    fun login(token: String?) {
        jasonService!!.setToken(token)
    }

    /**
     * Delete token asociated to the user.
     */
    fun logout(): Unit? {
        return jasonService?.logout()
    }

    /**
     *Send a rovert file to be processed in Jason and return a Single <SubmitProcessResult>
     * @param type of process to be performed {@sample GNSS, CONVERTER}
     * @param roverFile
     * @return Single<[SubmitProcessResult]>
     */
    fun submitProcess(type: String, roverFile: File): Single<SubmitProcessResult> {
        return jasonService!!.submitProcess(type, roverFile)
    }

    /**
     * Perform a PPK processing specifying a rover, base file as well as the location of the base
     * station and return a Single <SubmitProcessResult>
     * @param type of process to be performed {@sample GNSS, CONVERTER}
     * @param roverFile
     * @param baseFile
     * @param location
     * @return Single<[SubmitProcessResult]> which is the API response
     */
    fun submitProcess(type: String, roverFile: File, baseFile: File, location: Location) :Single<SubmitProcessResult> {
        return jasonService!!.submitProcess(type, roverFile, baseFile, location)
    }

    /**
     * Get the logs that are made when the process is running or have been run
     * @param processId process ID
     * @param maxTimeoutMillis time to wait for a server respond
     *
     * @return Observable <[ProcessStatus]>
     */
    fun getProcessStatus(processId: Int, maxTimeoutMillis: Long = MAX_LOG_REQUEST_TIMEOUT_MS): Observable<ProcessStatus> {
        return jasonService!!.getProcessStatus(processId, maxTimeoutMillis)
    }

}







