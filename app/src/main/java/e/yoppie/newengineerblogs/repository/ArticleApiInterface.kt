package e.yoppie.newengineerblogs.repository

import e.yoppie.newengineerblogs.model.data.Company
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET

interface ArticleApiInterface {
//    @GET("test/companyList")
//    fun getCompanyList(): Deferred<List<Company>>

    @GET("test/companyList")
    fun getCompanyList(): Call<List<Company>>
}