package e.yoppie.newengineerblogs.repository

import e.yoppie.newengineerblogs.model.data.Companies
import io.reactivex.Observable
import retrofit2.http.GET

interface CompanyApiInterface {
    @GET("test/companyList")
    fun getCompanyList(): Observable<Companies>
}