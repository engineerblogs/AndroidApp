package e.yoppie.newengineerblogs.di.diModule

import android.arch.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import e.yoppie.newengineerblogs.model.data.Article
import javax.inject.Singleton

@Module
class ArticleViewModelModule(articleList: MutableList<Article>) {
    private var articleList: MutableList<Article> = mutableListOf()

    init {
        this.articleList = articleList
    }

    @Provides
    @Singleton
    fun provideArticleListLiveData(): MutableLiveData<MutableList<Article>>{
        val mutableLiveData = MutableLiveData<MutableList<Article>>()
        mutableLiveData.value = articleList
        return mutableLiveData
    }

    @Provides
    @Singleton
    fun provideArticleList(): MutableList<Article>{
        return articleList
    }
}
