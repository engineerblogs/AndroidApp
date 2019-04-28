package e.yoppie.newengineerblogs.repository

class FavoriteArticleRepository: BaseRepository() {
    fun getFavoriteArticles(userId: String) = this.retrofit
            .create(FavoriteArticlesApiInterface::class.java)
            .getFavoriteArticleList(userId)
}