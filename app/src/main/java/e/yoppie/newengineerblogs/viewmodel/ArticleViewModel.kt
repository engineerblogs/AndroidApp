package e.yoppie.newengineerblogs.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import e.yoppie.newengineerblogs.di.diInterface.DaggerArticleViewModelComponent
import e.yoppie.newengineerblogs.di.diModule.ArticleViewModelModule
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.repository.ArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleViewModel : ViewModel() {
    @Inject
    lateinit var articleListLiveData: MutableLiveData<MutableList<Article>>

    @Inject
    lateinit var articleList: MutableList<Article>

    private var articleRepository: ArticleRepository = ArticleRepository()

    fun set(articleList: MutableList<Article>) {
        val articleViewModelComponent = DaggerArticleViewModelComponent
                .builder()
                .articleViewModelModule(ArticleViewModelModule(articleList))
                .build()
        articleViewModelComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    fun loadArticles(companyId: String, offset: String) {
        articleRepository.getCategoryArticles(companyId, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    this.articleList = res.articles
                    this.articleListLiveData.postValue(this.articleList)
                }, { error ->
                    Log.d("yoshiya_debug", error.message)
                })
    }

//    fun loadMore() {
//        val article = Article(31, "たいとる31", "執筆者31", "https://developers.gnavi.co.jp/entry/slim-framework/")
//        if (this.articleList.last().id != article.id) {
//            this.articleList.add(article)
//            this.articleListLiveData.postValue(this.articleList)
//        }
//    }
}
