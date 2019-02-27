package e.yoppie.newengineerblogs.repository

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import e.yoppie.newengineerblogs.model.data.Company
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ArticleRepository(url: String) {

    private var retrofit: Retrofit

    val httpBuilder: OkHttpClient.Builder get() {
        // create http client
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val original = chain.request()

                    //header
                    val request = original.newBuilder()
                            .header("Accept", "application/json")
                            .method(original.method(), original.body())
                            .build()

                    return@Interceptor chain.proceed(request)
                })
                .readTimeout(30, TimeUnit.SECONDS)

        // log interceptor
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)

        return httpClient
    }

    init {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val gson = GsonBuilder()
                .serializeNulls()
                .create()

        this.retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(httpBuilder.build())
                .build()
    }

    fun test(): Response<List<Company>> {
        val service = this.retrofit.create(ArticleApiInterface::class.java)
        return service.getCompanyList().execute()
    }

//    private fun getClient(): OkHttpClient {
//        return OkHttpClient
//                .Builder()
//                .connectTimeout(120, TimeUnit.SECONDS)
//                .readTimeout(120, TimeUnit.SECONDS)
//                .addInterceptor(HttpLoggingInterceptor().apply {
//                    level = HttpLoggingInterceptor.Level.BODY
//                })
//                .build()

//    }
}