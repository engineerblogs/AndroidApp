package e.yoppie.newengineerblogs.repository

import android.content.Context
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import e.yoppie.newengineerblogs.model.room.AppDatabase
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ArticleRepository {

    companion object {
        const val END_POINT = "https://9hqe5z0uw7.execute-api.ap-northeast-1.amazonaws.com"
    }

    private var retrofit: Retrofit

    init {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        this.retrofit = Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(getClient())
                .build()
    }

    private fun getClient() = OkHttpClient
            .Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    fun getLocalSavedCompanyList(context: Context): List<CompanyEntity> {
        val db = AppDatabase.getInstance(context)!!
        return db.companyDao().findAll()
    }
}