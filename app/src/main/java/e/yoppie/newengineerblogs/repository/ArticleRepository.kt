package e.yoppie.newengineerblogs.repository

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import e.yoppie.newengineerblogs.model.data.Company
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ArticleRepository(url: String) {

    private var retrofit: Retrofit

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
                .client(getClient())
                .build()
    }

    suspend fun test(): List<Company> {
        val service = this.retrofit.create(ArticleApiInterface::class.java)
        return service.getCompanyList().await()
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient
                .Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
    }
}