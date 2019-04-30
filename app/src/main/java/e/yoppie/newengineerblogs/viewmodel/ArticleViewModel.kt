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
        articleRepository.getCategoryArticles(companyId, offset, "userId")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    this.articleList = res.articles
                    this.articleListLiveData.postValue(this.articleList)
                }, { error ->
                    Log.d("yoppie_debug", error.message)
                })
    }

//    @SuppressLint("CheckResult")
//    fun loadMore(companyId: String) {
//        articleRepository.getCategoryArticles(companyId, articleList.size.toString(), "userId")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ res ->
//                    this.articleList.addAll(res.articles)
//                    this.articleListLiveData.postValue(this.articleList)
//                }, { error ->
//                    Log.d("yoppie_debug", error.message)
//                })
//    }
}
