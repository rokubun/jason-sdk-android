package cat.rokubun.jason


import android.content.Context
import cat.rokubun.jason.repository.JasonService
import cat.rokubun.jason.repository.remote.dto.SubmitProcessResult
import cat.rokubun.jason.utils.SingletonHolder
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import java.io.File

/**
 * JasonClient is responsible for communicating with JASON related services
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
     * Provides a JasonClient singleton.
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
     * Perform the Login process to Jason and return a Single<User> object.
     * @param email user's email
     * @param password  user's password
     * @return Single<[User]>
     */
    fun login(email: String?, password: String?): Single<User>? {
        return jasonService?.login(email, password)
    }

    /**
     * Set token instead of retrieving it with the login.
     * @param token user's token
     */
    fun login(token: String?) {
        jasonService!!.setToken(token)
    }

    /**
     * Logout from JASOn by removing the token associated to the user.
     */
    fun logout(): Unit? {
        return jasonService?.logout()
    }

    /**
     * Perform a PPK processing with JASON specifying a Rover file and returns a
     * Single<SubmitProcessResult>
     * Compatible files are Argounaut, Ublox, Septentrio, Rinex 2/3 devices and
     * Android GnssLogger, GPS_Test and GalileoPVT applications
     *
     * @param type of process to be performed {@sample GNSS, CONVERTER}
     * @param roverFile Input file to be send to Jason
     * @return Single<[SubmitProcessResult]>
     */
    fun submitProcess(label: String, type: String, roverFile: File): Single<SubmitProcessResult> {
        return jasonService!!.submitProcess(label, type, roverFile)
    }

    /**
     * Perform a PPK processing specifying a rover, base file as well as the location of the base
     * station and return a Single <SubmitProcessResult>
     * Compatible files are Argounaut, Ublox, Septentrio, Rinex 2/3 devices and
     * Android GnssLogger, GPS_Test and GalileoPVT applications
     * @param type of process to be performed {@sample GNSS, CONVERTER}
     * @param roverFile
     * @param baseFile
     * @param location
     * @return Single<[SubmitProcessResult]> which is the API response
     */
    fun submitProcess(label: String, type: String, roverFile: File, baseFile: File, location: Location) :Single<SubmitProcessResult> {
        return jasonService!!.submitProcess(label, type, roverFile, baseFile, location)
    }

    /**
     * Perform a PPK processing specifying a rover, base file as well as the location of the base
     * station and return a Single <SubmitProcessResult>
     * Compatible files are Argounaut, Ublox, Septentrio, Rinex 2/3 devices and
     * Android GnssLogger, GPS_Test and GalileoPVT applications
     * @param type of process to be performed {@sample GNSS, CONVERTER}
     * @param roverFile
     * @param baseFile
     * @param location
     * @return Single<[SubmitProcessResult]> which is the API response
     */
    fun retryProcess(processId: Int): Single<SubmitProcessResult> {
        return jasonService!!.retryProcess(processId)
    }

    /**
     * Get process status with the log and the results. The Observable will be updated every one
     * second until the processing finishes, either successful or with an error.
     * @param processId process ID
     * @param maxTimeoutMillis time to wait for a server respond
     *
     * @return Observable <[ProcessStatus]>
     */
    fun getProcessStatus(processId: Int, maxTimeoutMillis: Long = MAX_LOG_REQUEST_TIMEOUT_MS): Observable<ProcessStatus> {
        return jasonService!!.getProcessStatus(processId, maxTimeoutMillis)
    }

    /**
     * Get user's processes
     * @param toke user's token
     * @return Single <List<[ProcessInfo]>>
     */
    fun getProcesses(token: String): Single<List<ProcessInfo>>{
        return jasonService!!.getProcesses(token)
    }


}







