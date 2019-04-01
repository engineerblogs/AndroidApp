package e.yoppie.newengineerblogs.repository

import e.yoppie.newengineerblogs.model.data.Categories
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApiInterface {
    @GET("test/articleList")
    fun getArticleList(
            @Query("deviceId") deviceId: String,
            @Query("limit") limit: String
    ): Observable<Categories>
}