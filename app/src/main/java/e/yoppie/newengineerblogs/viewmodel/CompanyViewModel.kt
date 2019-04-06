package e.yoppie.newengineerblogs.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.model.data.Category
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity
import e.yoppie.newengineerblogs.repository.ArticleRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CompanyViewModel : ViewModel() {

    var categoryListData: MutableLiveData<MutableList<Category>>
    private var articleRepository: ArticleRepository = ArticleRepository()

    init {
        val mutableLiveData = MutableLiveData<MutableList<Category>>()
        val articles = mutableListOf(
                Article("100", "title", "thumnail", "url", "publishedDate", "author")
        )
        mutableLiveData.value = mutableListOf(
                Category("yoyo", "100", articles)
        )
        categoryListData = mutableLiveData
    }

    @SuppressLint("CheckResult")
    private fun loadAllArticles(reloadTab: () -> Unit) {
        articleRepository.getAllCategoryArticles("111", "5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    val mutableLiveData = MutableLiveData<MutableList<Category>>()
                    mutableLiveData.value = res.categories
                    categoryListData = mutableLiveData
                    reloadTab()
                }, { error ->
                    Log.d("yoshiya_debug", error.message)
                })
    }

    @SuppressLint("CheckResult")
    fun getSavedCompanyList(intentMethod: () -> Unit, reloadTab: () -> Unit, context: Context) {
        var localCompanyEntitiyList: List<CompanyEntity> = mutableListOf()
        Completable
                .fromAction {
                    localCompanyEntitiyList = articleRepository.getLocalSavedCompanyList(context)
                }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (localCompanyEntitiyList.isEmpty()) {
                        intentMethod()
                    } else {
                        loadAllArticles(reloadTab)
                    }
                }
    }
}
