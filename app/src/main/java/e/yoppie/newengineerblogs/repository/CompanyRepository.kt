package e.yoppie.newengineerblogs.repository

import android.util.Log
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import e.yoppie.newengineerblogs.model.data.Companies
import e.yoppie.newengineerblogs.model.data.Company
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
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
                .client(getClient())
                .build()
    }

    fun getCompanies(): MutableList<Company> {
        var companyList: MutableList<Company> = mutableListOf()

        try {
            val companyApiInterface = this.retrofit.create(CompanyApiInterface::class.java)
            companyApiInterface.getCompanyList().enqueue(object : Callback<Companies> {
                override fun onFailure(call: Call<Companies>, t: Throwable) {
                    // todo: エラーハンドリング
                    Log.d("yoshiya_debug", t.message)
                }

                override fun onResponse(call: Call<Companies>, response: Response<Companies>) {
                    if (response.isSuccessful) {
                        companyList = response.body()!!.companies
                    }
                }
            })
        } catch (t: Throwable) {
            // todo: エラーハンドリング
            Log.d("yoshiya_debug", t.message)
        }

        return companyList
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