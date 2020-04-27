package cat.rokubun.jason.repository.remote.dto

/**
 * User login response.
 */
data class UserLoginResult(
    val id: Int,
    val email: String,
    val name: String,
    val surname: String,
    val created: String,
    val type: String,
    val token: String
)

