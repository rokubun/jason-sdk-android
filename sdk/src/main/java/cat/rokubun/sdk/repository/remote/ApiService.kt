package cat.rokubun.sdk.repository.remote

import cat.rokubun.sdk.repository.remote.dto.SubmitProcessResult
import cat.rokubun.sdk.repository.remote.dto.UserLoginResult
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("users/sessions")
    fun userlogin(@Field("email") email: String?, @Field("password") password: String?): Call<UserLoginResult>


    /*
    curl -X POST "http://api-argonaut.rokubun.cat:80/api/processes/"
    -H  "accept: application/json"
    -H  "ApiKey: ARGONAUT.BOF.LQIGHJEYRT754651059DJ5UFM59MS93M"
    -H  "Content-Type: multipart/form-data"
    -F "token=38289D-9F63D5-6E0DA1-ED4539-8E8480"
    -F "type=GNSS"
    -F "rover_file=@jason_gnss_test_file_rover.txt;type=text/plain"
     */
    @Multipart
    @POST("processes/")
    fun submitProcess(@Part ("token") token: RequestBody,
                      @Part ("type") type: String,
                      @Part rover_file: MultipartBody.Part): Call<SubmitProcessResult>


    
}