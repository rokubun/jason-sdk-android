package cat.rokubun.sdk.domain

/**
 *  User information
 *  @property name user name
 *  @property surname user surname
 *  @property secretToken user token
 *  @property email user email
 *  @property id user  ID
 *  @property type user type
 * */


data class User(
    var name: String?,
    var surname: String?,
    var secretToken: String?,
    var email: String?,
    var id: Int?,
    var type: String?)

