package e.yoppie.newengineerblogs.di.diModule

import android.arch.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import e.yoppie.newengineerblogs.model.data.Article
import javax.inject.Singleton

@Module
class FavoriteArticleViewModelModule {

    private val articleList = mutableListOf(
            Article("100", "title", "thumbnail", "url", "publishedDate", "author")
    )

    @Singleton
    @Provides
    fun provideFavoriteArticleListLiveData(): MutableLiveData<MutableList<Article>> {
        val mutableLiveData = MutableLiveData<MutableList<Article>>()
        mutableLiveData.value = articleList
        return mutableLiveData
    }

    @Singleton
    @Provides
    fun provideFavoriteArticleList(): MutableList<Article> {
        return articleList
    }
}