package cat.rokubun.jason.repository.remote.dto

import cat.rokubun.jason.ProcessResult
import com.google.gson.annotations.SerializedName

/**
 *  Processes info response
 */
data class ProcessApiResult (

    @SerializedName("id") val id : Int,
    @SerializedName("user_id") val user_id : Int,
    @SerializedName("type") val type : String,
    @SerializedName("status") val status : String,
    @SerializedName("source_file") val sourceFile : String,
    @SerializedName("dynamic") val dynamic : Int,
    @SerializedName("source_base_file") val sourceBaseFile : String,
    @SerializedName("base_position") val basePosition : String,
    @SerializedName("created") val created : String,
    @SerializedName("label") val label : String,
    @SerializedName("email") val email : String,
    @SerializedName("user_name") val userName : String,
    @SerializedName("user_surname") val userSurname : String,
    @SerializedName("results") val results : List<ResultsResponse>
)