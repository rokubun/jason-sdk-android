package cat.rokubun.sdk.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import cat.rokubun.sdk.BuildConfig
import cat.rokubun.sdk.utils.NetworkConnectivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ServiceFactory(private val context: Context) {
    //Creating Auth Interceptor to add api_key query in front of all the requests

    val networkInterceptor: Interceptor = NetworkConnectivity(context)
    var retrofit: Retrofit? = null
    fun getClient(baseUrl: String, apiKey: String): Retrofit? {
        val client = OkHttpClient.Builder()
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
                client.addInterceptor(Interceptor { chain ->
                    val original = chain.request()
                    //header
                    val request = original.newBuilder()
                        .header("accept", "application/json")
                        .header("ApiKey", apiKey)
                        .build()
                    return@Interceptor chain.proceed(request)
                })
                .addInterceptor(networkInterceptor)
                .build()
            interceptor.level =  HttpLoggingInterceptor.Level.BODY
            //FIXME DEBUGMODE
            //client.addInterceptor(interceptor)

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    return retrofit
    }
}