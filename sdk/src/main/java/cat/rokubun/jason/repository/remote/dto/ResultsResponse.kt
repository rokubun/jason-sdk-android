package cat.rokubun.jason.repository.remote.dto

/**
 * Result response.
 */

data class ResultsResponse (

    val id : Int,
    val processId : Int,
    val url : String,
    val created : String,
    val name : String,
    val staticPos : String,
    val extension : String
)