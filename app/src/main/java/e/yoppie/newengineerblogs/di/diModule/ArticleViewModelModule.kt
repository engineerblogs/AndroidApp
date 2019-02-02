package e.yoppie.newengineerblogs.di.diModule

import android.arch.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import e.yoppie.newengineerblogs.model.data.Article
import javax.inject.Singleton

@Module
class ArticleViewModelModule(private val articleList: MutableList<Article>) {

    @Provides
    @Singleton
    fun provideArticleListLiveData(): MutableLiveData<MutableList<Article>> {
        val mutableLiveData = MutableLiveData<MutableList<Article>>()
        mutableLiveData.value = articleList
        return mutableLiveData
    }

    @Provides
    @Singleton
    fun provideArticleList(): MutableList<Article> {
        return articleList
    }
}
