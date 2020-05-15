package cat.rokubun.jason.repository.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Result response.
 */

data class ResultsResponse  (

    @SerializedName("type") val type : String ?= "",
    @SerializedName("name") val name : String ?= "",
    @SerializedName("value") val value : String ?= ""
)