package e.yoppie.newengineerblogs.repository

import e.yoppie.newengineerblogs.model.data.Companies
import retrofit2.Call
import retrofit2.http.GET

interface CompanyApiInterface {
    @GET("test/companyList")
    fun getCompanyList(): Call<Companies>
}