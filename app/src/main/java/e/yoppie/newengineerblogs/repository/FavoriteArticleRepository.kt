package e.yoppie.newengineerblogs.repository

import retrofit2.create

class FavoriteArticleRepository: BaseRepository() {
    fun getFavoriteArticles(userId: String) = this.retrofit
            .create(FavoriteArticlesApiInterface::class.java)
            .getFavoriteArticleList(userId)
}