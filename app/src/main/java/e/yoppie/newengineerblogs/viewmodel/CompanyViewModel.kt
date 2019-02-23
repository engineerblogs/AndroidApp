package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.model.data.Category
import e.yoppie.newengineerblogs.repository.ArticleRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CompanyViewModel : ViewModel() {

    var categoryList: MutableLiveData<List<Category>>

    init {
        GlobalScope.launch { loadTest() }
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

    private suspend fun loadTest(){
        try{
            val articleRepository = ArticleRepository("https://9hqe5z0uw7.execute-api.ap-northeast-1.amazonaws.com")
            val name = articleRepository.test()[0].name
            Log.d("yoshiya_debug", name)
        }catch (t: Throwable){
            Log.d("yoshiya_debug", "きてない")
        }
    }

}
