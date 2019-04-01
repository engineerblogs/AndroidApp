package e.yoppie.newengineerblogs.repository

import e.yoppie.newengineerblogs.model.data.Categories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApiInterface {
    @GET("test/articleList")
    fun getArticleList(
            @Query("deviceId") deviceId: String,
            @Query("limit") limit: String
    ): Call<Categories>
}