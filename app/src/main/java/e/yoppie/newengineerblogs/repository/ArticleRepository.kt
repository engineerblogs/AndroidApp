package e.yoppie.newengineerblogs.repository

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import e.yoppie.newengineerblogs.model.data.Companies
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ArticleRepository(url: String) {

    private var retrofit: Retrofit

    init {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        this.retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(getClient())
                .build()
    }

    fun getCompanies(): Response<Companies> {
        val service = this.retrofit.create(ArticleApiInterface::class.java)
        return service.getCompanyList().execute()
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