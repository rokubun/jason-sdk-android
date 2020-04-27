package cat.rokubun.jason.repository.remote.dto

/**
 *  Process Detail response.
 */

data class ProcessDetailResult (

    val id : Int,
    val user_id : Int,
    val type : String,
    val status : String,
    val source_file : String,
    val source_base_file : String,
    val camera_metadata_file : String,
    val config_file : String,
    val created : String,
    val finished : String
)