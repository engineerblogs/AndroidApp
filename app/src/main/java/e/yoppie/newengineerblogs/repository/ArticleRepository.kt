package e.yoppie.newengineerblogs.repository

import android.content.Context
import e.yoppie.newengineerblogs.model.room.AppDatabase
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity

class ArticleRepository: BaseRepository() {

    fun getLocalSavedCompanyList(context: Context): List<CompanyEntity> {
        val db = AppDatabase.getInstance(context)!!
        return db.companyDao().findAll()
    }

    fun getAllCategoryArticles(deviceId: String, limit: String) = this.retrofit
            .create(CategoryArticlesApiInterface::class.java)
            .getArticleList(deviceId, limit)

    fun getCategoryArticles(companyId: String, offset: String) = this.retrofit
            .create(ArticlesApiInterface::class.java)
            .getArticleList(companyId, offset)
}