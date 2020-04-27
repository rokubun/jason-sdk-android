package cat.rokubun.jason.repository

import android.content.Context
import cat.rokubun.jason.utils.NetworkConnectivityInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create an implementation of Retrofit to communicate with a service API.
 * @see HttpLoggingInterceptor
 * @see Retrofit
 */
class ServiceFactory(private val context: Context) {
    var retrofit: Retrofit? = null
    /**
     * Se encarga de realizar peticiones HTTP
     * @param baseUrl url for the API request
     * @param apiKey for the request
     * @return An implementation of the given Rest API
     */
    fun getService(baseUrl: String, apiKey: String): Retrofit? {
        if (retrofit == null) {
            val client = OkHttpClient.Builder()
            //Increment log details
            val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val networkInterceptor: Interceptor = NetworkConnectivityInterceptor(context)

            client.addInterceptor(apiKeyInterceptor(apiKey))
                .addInterceptor(networkInterceptor)
                .addInterceptor(httpLoggingInterceptor) // FIXME Only on DEBUG mode
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return retrofit
    }

    /**
     * Add the API KEY to header on the request
     * @param apiKey for the request
     * @return interceptor whith added header
     */
    private fun apiKeyInterceptor(apiKey: String): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            //header
            val request = original.newBuilder()
                .header("accept", "application/json")
                .header("ApiKey", apiKey)
                .build()
            return@Interceptor chain.proceed(request)
        }
    }

}