package cat.rokubun.sdk.repository.remote

import cat.rokubun.sdk.repository.remote.dto.UserLoginResult
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("sessions")
    fun userlogin(@Field("email") email: String, @Field("password") password: String): Call<UserLoginResult>
    fun userlogin(@Field("email") email: String?, @Field("password") password: String?): Call<UserLoginResult>



}