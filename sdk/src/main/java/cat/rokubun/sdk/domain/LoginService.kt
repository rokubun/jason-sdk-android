package cat.rokubun.sdk.domain

import android.content.Context
import cat.rokubun.sdk.JasonClient
import cat.rokubun.sdk.repository.ServiceFactory
import cat.rokubun.sdk.repository.remote.ApiService
import cat.rokubun.sdk.repository.remote.dto.UserLoginResult
import cat.rokubun.sdk.utils.Hasher
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService {

    private var apiService: ApiService
    private var serviceFactory: ServiceFactory? = null

    constructor(context: Context) {
        serviceFactory = ServiceFactory(context)
        apiService = serviceFactory!!.getService(
            JasonClient.URL,
            JasonClient.API_KEY
        )?.create(ApiService::class.java)!!
    }

    fun login(user: String?, password: String?): Single<User> {
        return Single.create { emitter ->
            apiService?.userlogin(user, Hasher.hash(password))
                ?.enqueue((object : Callback<UserLoginResult> {
                    override fun onFailure(call: Call<UserLoginResult>, t: Throwable) {
                        emitter.onError(Throwable(ResponseCodeEum.ERROR.description))
                    }

                    override fun onResponse(
                        call: Call<UserLoginResult>,
                        response: Response<UserLoginResult>
                    ) {
                        when (response.code()) {
                            200 -> {
                                val userResponse = User(
                                    response.body()?.name,
                                    response.body()?.surname,
                                    response.body()!!.token,
                                    response.body()?.email,
                                    response.body()?.id
                                )
                                emitter.onSuccess(userResponse!!)
                            }
                            401 -> {
                                emitter.onError(Throwable(ResponseCodeEum.FORBIDDEN.description))
                            }
                        }
                    }
                }))
        }
    }

    enum class ResponseCodeEum(val code: Int, val description: String) {
        OK(200, "Login susccess"),
        FORBIDDEN(401, "User or password incorrect"),
        ERROR(500, "Service is no available")

    }
}