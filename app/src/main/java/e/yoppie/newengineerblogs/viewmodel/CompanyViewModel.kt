package e.yoppie.newengineerblogs.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import e.yoppie.newengineerblogs.di.diInterface.DaggerCompanyViewModelComponent
import e.yoppie.newengineerblogs.di.diModule.CompanyViewModelModule
import e.yoppie.newengineerblogs.model.data.Category
import e.yoppie.newengineerblogs.repository.ArticleRepository
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
        articleRepository.getAllCategoryArticles("111")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    val mutableLiveData = MutableLiveData<MutableList<Category>>()
                    mutableLiveData.value = res.categories
                    categoryListData = mutableLiveData
                    reloadTab()
                    this.isLoad = false
                }, { error ->
                    Log.d("yoppie_debug", error.message)
                })
    }

//    @SuppressLint("CheckResult")
//    fun getSavedCompanyList(transition: () -> Unit, reloadTab: () -> Unit, context: Context) {
//        var localCompanyEntityList: List<CompanyEntity> = mutableListOf()
//        Completable
//                .fromAction {
//                    localCompanyEntityList = articleRepository.getLocalSavedCompanyList(context)
//                }
//                .subscribeOn(Schedulers.io())
//                .subscribe {
//                    if (localCompanyEntityList.isEmpty()) {
//                        transition()
//                    } else {
//                        loadAllArticles(reloadTab)
//                    }
//                }
//    }
}
