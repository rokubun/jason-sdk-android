package cat.rokubun.sdk.repository

import android.content.Context
import cat.rokubun.sdk.utils.NetworkConnectivityInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ServiceFactory(private val context: Context) {
    var retrofit: Retrofit? = null

    fun getService(baseUrl: String, apiKey: String): Retrofit? {
        if (retrofit == null) {
            val client = OkHttpClient.Builder()
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