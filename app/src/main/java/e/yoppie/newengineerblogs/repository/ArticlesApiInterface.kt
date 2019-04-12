package e.yoppie.newengineerblogs.repository

import e.yoppie.newengineerblogs.model.data.Category
import e.yoppie.newengineerblogs.model.data.Companies
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApiInterface {
    @GET("test/articleList")
    fun getArticleList(
            @Query("companyId") companyId: String,
            @Query("offset") offset: String
    ): Observable<Category>
}