package cat.rokubun.sdk.repository

import android.content.Context
import android.util.Log
import android.webkit.MimeTypeMap
import cat.rokubun.sdk.JasonClient
import cat.rokubun.sdk.domain.Location
import cat.rokubun.sdk.domain.User
import cat.rokubun.sdk.repository.remote.ApiService
import cat.rokubun.sdk.repository.remote.dto.SubmitProcessResult
import cat.rokubun.sdk.repository.remote.dto.UserLoginResult
import cat.rokubun.sdk.utils.Hasher
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class JasonService {

    private var apiService: ApiService
    private var serviceFactory: ServiceFactory? = null

    private var token: String? = null

    constructor(context: Context) {
        serviceFactory = ServiceFactory(context)
        apiService = serviceFactory!!.getService(
            JasonClient.URL,
            JasonClient.API_KEY
        )?.create(ApiService::class.java)!!
    }

    fun login(user: String?, password: String?): Single<User> {
        return Single.create { emitter ->
            apiService.userlogin(user, Hasher.hash(password))
                .enqueue((object : Callback<UserLoginResult> {
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
                                token = response.body()!!.token
                                Log.d("DEBUG", "login token = "+token)
                                emitter.onSuccess(userResponse)
                            }
                            401 -> {
                                emitter.onError(Throwable(ResponseCodeEum.FORBIDDEN.description))
                            }
                        }
                    }
                }))
        }
    }

    fun submitProcess(type: String, roverFile: File, baseFile: File? = null, location: Location? = null) {
        val requestFile = roverFile.asRequestBody(getMimeType(roverFile.name)?.toMediaTypeOrNull())
        val roverPartFile = MultipartBody.Part.createFormData("rover_file", roverFile.name, requestFile)
        Log.d("DEBUG", "token " + token)
        val secretToken = token!!.toRequestBody()
        val typePart = type.toRequestBody()

        if (baseFile == null) {
            apiService.submitProcess(secretToken, typePart, roverPartFile)
                .enqueue(submitProcessCallback)
        } else {
            val requestBaseFile =
                roverFile.asRequestBody(getMimeType(baseFile.name)?.toMediaTypeOrNull())
            val basePartFile =
                MultipartBody.Part.createFormData("base_file", roverFile.name, requestBaseFile)

            val requestLocation = location?.toQueryString()?.toRequestBody()
            apiService.submitProcess(secretToken,
                typePart,
                roverPartFile,
                basePartFile,
                requestLocation)
                .enqueue(submitProcessCallback)
        }
    }


    object submitProcessCallback : Callback<SubmitProcessResult> {
        override fun onFailure(call: Call<SubmitProcessResult>, t: Throwable) {
            Log.e("Submit: ", "error:", t.cause)
        }

        override fun onResponse(
            call: Call<SubmitProcessResult>,
            response: Response<SubmitProcessResult>
        ) {
            Log.d("Response:", response.message())
            SubmitProcessResult(response.body()?.id, response.body()?.message)
        }
    }

    enum class ResponseCodeEum(val code: Int, val description: String) {
        OK(200, "Success"),
        FORBIDDEN(401, "User or password incorrect"),
        ERROR(500, "Service is no available")

    }

    private fun getMimeType(url: String?): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }
}