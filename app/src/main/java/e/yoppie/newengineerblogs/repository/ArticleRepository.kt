package e.yoppie.newengineerblogs.repository

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
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
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(getClient())
                .build()
    }

    fun test() {
        val service = this.retrofit.create(ArticleApiInterface::class.java)
        try {
            val response = service.getCompanyList().execute()
            if(response.isSuccessful){
                Log.d("yoshiya_debug", response.body()!!.name)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
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