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
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CompanyViewModel : ViewModel() {

    var categoryList: MutableLiveData<List<Category>>
    private var articleRepository: ArticleRepository = ArticleRepository()

    init {
        categoryList = loadAllCategoryArticles()
    }

    private fun loadAllCategoryArticles(): MutableLiveData<List<Category>> {
        val articles = mutableListOf(
                Article(1, "たいとる1", "執筆者1", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(2, "たいとる2", "執筆者2", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(3, "たいとる3", "執筆者3", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(4, "たいとる4", "執筆者4", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(5, "たいとる5", "執筆者5", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(6, "たいとる6", "執筆者6", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(7, "たいとる7", "執筆者7", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(8, "たいとる8", "執筆者8", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(9, "たいとる9", "執筆者9", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(10, "たいとる10", "執筆者10", "https://developers.gnavi.co.jp/entry/slim-framework/")
        )
        val mutableLiveData = MutableLiveData<List<Category>>()
        mutableLiveData.value = listOf(
                Category(1, "かてごりー1", articles),
                Category(2, "かてごりー2", articles),
                Category(3, "かてごりー3", articles),
                Category(4, "かてごりー4", articles),
                Category(5, "かてごりー5", articles),
                Category(6, "かてごりー6", articles),
                Category(7, "かてごりー7", articles)
        )
        return mutableLiveData

        // todo: modelLayerから取得する処理に修正せよ
    }

    @SuppressLint("CheckResult")
    fun getSavedCompanyList(context: Context){
        var localCompanyEntitiyList: List<CompanyEntity> = mutableListOf()
        Completable
                .fromAction {
                    localCompanyEntitiyList = articleRepository.getLocalSavedCompanyList(context)
                }
                .subscribeOn(Schedulers.io())
                .subscribe {
                }
    }

}
