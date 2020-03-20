package cat.rokubun.sdk.repository.remote.dto

import java.util.*


/*
{
  "id": 237,
  "email": "eduardo.paredes@rokubun.cat",
  "name": "",
  "surname": "",
  "created": "2020-03-18 14:01:31",
  "type": "REGULAR",
  "token": "5e722ca51e0227.98066014"
}
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

