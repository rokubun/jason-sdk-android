package cat.rokubun.sdk


import android.util.Log
import androidx.lifecycle.MutableLiveData
import cat.rokubun.sdk.repository.ServiceFactory
import cat.rokubun.sdk.domain.User
import cat.rokubun.sdk.repository.remote.ApiService
import cat.rokubun.sdk.repository.remote.dto.UserLoginResult
import cat.rokubun.sdk.utils.Hasher
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object JasonClient {
    //TODO EXTRACT TO STRING.xml
    private var URL: String = "http://api-argonaut.rokubun.cat:80/api/users/"
    private var API_KEY = "ARGONAUT.BOF.LQIGHJEYRT754651059DJ5UFM59MS93M"
    private val retrofitInstance: ApiService
    var codeResponse = MutableLiveData<ResponseCodeEum>()
    var user : User?= null
    init {
         retrofitInstance = ServiceFactory.getClient(URL, API_KEY)?.create(ApiService::class.java)!!
    }

    fun login(email: String?, password: String?): Single<ResponseCodeEum?>? {

        return Single.create {retrofitInstance.userlogin(email, Hasher.hash(password)).enqueue((object : Callback<UserLoginResult> {
                override fun onFailure(call: Call<UserLoginResult>, t: Throwable) {
                    Log.e("Login", "onFailure", t)
                    codeResponse.value = ResponseCodeEum.ERROR
                }

                override fun onResponse(call: Call<UserLoginResult>,
                                        response: Response<UserLoginResult>) {
                    when (response.code()) {
                        200 -> {
                            user = User(
                                response.body()?.name,
                                response.body()?.surname,
                                response.body()?.token,
                                response.body()?.email,
                                response.body()?.id
                            )
                            Log.d("response", user?.email +" "+user?.secretToken)
                            Log.d("response.body",  response.body()?.id.toString() + " " +response.body()?.token
                            )
                            codeResponse.postValue(ResponseCodeEum.OK)
                        }
                        401 -> codeResponse.postValue(ResponseCodeEum.FORBIDDEN)
                    }
                }
            }))}


    }


    fun submitProcess() {
//        retrofitInstance.submitProcess(this.user.sec)


    }

    enum class ResponseCodeEum(val code: Int, val description: String){
        OK(200, "Login susccess"),
        FORBIDDEN(401, "User or password incorrect"),
        ERROR(500, "Internal server error")
    }
}







