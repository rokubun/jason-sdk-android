package cat.rokubun.sdk


import android.util.Log
import cat.rokubun.sdk.repository.ServiceFactory
import cat.rokubun.sdk.domain.User
import cat.rokubun.sdk.repository.remote.ApiService
import cat.rokubun.sdk.repository.remote.dto.UserLoginResult
import cat.rokubun.sdk.utils.Hasher


object JasonClient {
    //TODO EXTRACT TO STRING.xml
    private var URL: String = "http://api-argonaut.rokubun.cat:80/api/users/"
    private var API_KEY = "ARGONAUT.BOF.LQIGHJEYRT754651059DJ5UFM59MS93M"
    var isValid: Boolean? = null

    var user : User?= null

    fun login(email: String?, password: String?) {
        val retrofitInstance = ServiceFactory.getClient(URL, API_KEY)?.create(ApiService::class.java)
        retrofitInstance?.userlogin(email, Hasher.hash(password))?.enqueue((object : retrofit2.Callback<UserLoginResult> {
            override fun onFailure(call: retrofit2.Call<UserLoginResult>, t: Throwable) {
                Log.e("Login", "onFailure", t)
            }
            override fun onResponse(call: retrofit2.Call<UserLoginResult>, response: retrofit2.Response<UserLoginResult>) {
                if(response.code() == 200) {
                    user = User(
                        response.body()?.id,
                        response.body()?.email,
                        response.body()?.token
                    )
                }
                //FIXME SYNC VALUE
                isValid = response.isSuccessful
                Log.d("Login", "onResponse "+response.code().toString()+ " "+ response.isSuccessful.toString())
            }
        }))
        Log.d("Log", "valid " + isValid)
    }
}





