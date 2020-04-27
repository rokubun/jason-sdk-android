package cat.rokubun.jason.repository.remote.dto

/**
 * Log response.
 */

data class Log (
    val id : Int,
    val process_id : Int,
    val level : String,
    val message : String,
    val created : String
)