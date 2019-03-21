package e.yoppie.newengineerblogs.repository

import android.content.Context
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import e.yoppie.newengineerblogs.model.room.AppDatabase
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class CompanyRepository {

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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(getHttpClient())
                .build()
    }

    fun getCompanyList() = this.retrofit
            .create(CompanyApiInterface::class.java)
            .getCompanyList()

    private fun getHttpClient() = OkHttpClient
            .Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    fun saveCompanyList(companyIdList: List<String>, context: Context) {
        // todo: RetrofitでDynamoDBにデータを保存するAPIを叩く

        val companyEntityList: MutableList<CompanyEntity> = mutableListOf()
        companyIdList.forEach {
            companyEntityList.add(CompanyEntity.create(it))
        }

        val db = AppDatabase.getInstance(context)!!
        db.companyDao().insertItems(companyEntityList)
    }
}
