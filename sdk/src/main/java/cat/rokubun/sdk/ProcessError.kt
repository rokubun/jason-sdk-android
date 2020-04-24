package cat.rokubun.sdk

import okhttp3.internal.http2.Http2
import okhttp3.internal.http2.Http2Connection

/**
 * Contains error messages that return API requests
 * @constructor http code response
 */
class ProcessError(code: Int) {
    var code: Int? = null
    init {
        this.code = code
    }

    /**
     * return the error message according to the code
     */
    fun getErrorMessage(): String {
        return when (code) {
            204 -> "No content"
            404 -> "Not found"
            403 -> "Forbidden Process"
            500 -> "Internal server error"
            else -> "Something went wrong "+ "error: "+ code.toString()

        }
    }
}
