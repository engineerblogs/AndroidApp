package e.yoppie.newengineerblogs.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import e.yoppie.newengineerblogs.di.diInterface.DaggerCompanyViewModelComponent
import e.yoppie.newengineerblogs.di.diModule.CompanyViewModelModule
import e.yoppie.newengineerblogs.model.data.Category
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity
import e.yoppie.newengineerblogs.repository.ArticleRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Suppress("DEPRECATION")
class CompanyViewModel : ViewModel() {

    @Inject
    lateinit var categoryListData: MutableLiveData<MutableList<Category>>

    var isLoad: Boolean = false
    private var articleRepository: ArticleRepository = ArticleRepository()

    init {
        val companyViewModelComponent = DaggerCompanyViewModelComponent
                .builder()
                .companyViewModelModule(CompanyViewModelModule())
                .build()
        companyViewModelComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    fun loadAllArticles(reloadTab: () -> Unit) {
        articleRepository.getAllCategoryArticles("111", "5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    val mutableLiveData = MutableLiveData<MutableList<Category>>()
                    mutableLiveData.value = res.categories
                    categoryListData = mutableLiveData
                    reloadTab()
                    this.isLoad = false
                }, { error ->
                    Log.d("yoshiya_debug", error.message)
                })
    }

    @SuppressLint("CheckResult")
    fun getSavedCompanyList(transition: () -> Unit, reloadTab: () -> Unit, context: Context) {
        var localCompanyEntitiyList: List<CompanyEntity> = mutableListOf()
        Completable
                .fromAction {
                    localCompanyEntitiyList = articleRepository.getLocalSavedCompanyList(context)
                }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (localCompanyEntitiyList.isEmpty()) {
                        transition()
                    } else {
                        loadAllArticles(reloadTab)
                    }
                }
    }
}
