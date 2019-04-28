package e.yoppie.newengineerblogs.repository

import e.yoppie.newengineerblogs.model.data.Categories
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FavoriteArticlesApiInterface {
    @GET("test/favoriteArticle")
    fun getFavoriteArticleList(
            @Query("userId") userId: String
    ): Observable<Categories>
}