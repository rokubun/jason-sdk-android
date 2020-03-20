package cat.rokubun.sdk


import android.content.res.Resources
import android.util.Log
import cat.rokubun.sdk.repository.ServiceFactory
import cat.rokubun.sdk.domain.User
import cat.rokubun.sdk.repository.remote.ApiService
import cat.rokubun.sdk.repository.remote.dto.SingInBody
import cat.rokubun.sdk.repository.remote.dto.UserLoginResult
import cat.rokubun.sdk.utils.Hasher


object JasonClient {
    //TODO EXTRACT TO STRING.xml
    private var URL: String = "http://api-argonaut.rokubun.cat:80/api/users/"
    private var API_KEY = "ARGONAUT.BOF.LQIGHJEYRT754651059DJ5UFM59MS93M"

    var user : User?= null

    fun login(email: String, password: String):Boolean {
        println(URL + "\n"+ API_KEY)
                val retrofitInstance = ServiceFactory.getClient(URL, API_KEY)?.create(ApiService::class.java)
        var isConnect: Boolean = true
        retrofitInstance?.userlogin(email, Hasher.hash(password))?.enqueue((object : retrofit2.Callback<UserLoginResult> {
            override fun onFailure(call: retrofit2.Call<UserLoginResult>, t: Throwable) {
                Log.e("Login", "onFailure", t)
                isConnect = false
            }
            override fun onResponse(
                call: retrofit2.Call<UserLoginResult>,
                response: retrofit2.Response<UserLoginResult>
            ) {
                if(response.isSuccessful) {
                    user = User(
                        response.body()?.id,
                        response.body()?.email,
                        response.body()?.token
                    )
                }
                Log.d("Login", "onResponse"+response.code().toString())
            }
        }))
        return isConnect
    }
}





