package cat.rokubun.sdk.domain

import java.util.*

data class Location (
    val latitude: Double,
    val longitude: Double,
    val height: Double


) {
    fun toQueryString(): String {
        return String.format(
            Locale.ENGLISH, "%.08f,%.08f,%.08f",
            latitude,
            longitude,
            height
        )
    }
}
