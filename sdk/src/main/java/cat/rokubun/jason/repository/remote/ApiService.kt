package cat.rokubun.jason.repository.remote

import cat.rokubun.jason.repository.remote.dto.ProcessStatusResult
import cat.rokubun.jason.repository.remote.dto.SubmitProcessResult
import cat.rokubun.jason.repository.remote.dto.UserLoginResult
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/**
 * Interface to make API requests
 * @see POST
 * @see GET
 * @see FormUrlEncoded
 * @see Multipart
 * @see Response
 */
interface ApiService {

    @FormUrlEncoded
    @POST("users/sessions")
    fun userlogin(@Field("email") email: String?, @Field("password") password: String?): Call<UserLoginResult>

    @Multipart
    @POST("processes/")
    fun submitProcess(@Part ("token") token: RequestBody,
                      @Part ("type") type: RequestBody,
                      @Part rover_file: MultipartBody.Part): Call<SubmitProcessResult>
    @Multipart
    @POST("processes/")
    fun submitProcess(@Part ("token") token: RequestBody,
                      @Part ("type") type: RequestBody,
                      @Part rover_file: MultipartBody.Part,
                      @Part base_file: MultipartBody.Part,
                      @Part ("location") location: RequestBody?): Call<SubmitProcessResult>

    @GET( "processes/{id}")
    suspend fun getProcessInformation(@Path("id") processId: Int, @Query("token") token: String): Response<ProcessStatusResult>
}

