package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.model.data.Category

class CompanyViewModel : ViewModel() {

    var categoryList: MutableLiveData<List<Category>>? = null

    init {
        categoryList = loadAllCategoryArticles()
    }

    private fun loadAllCategoryArticles(): MutableLiveData<List<Category>> {
        val articles = listOf(
                Article(1, "たいとる1", "執筆者1", "URL1"),
                Article(2, "たいとる2", "執筆者2", "URL2"),
                Article(3, "たいとる3", "執筆者3", "URL3"),
                Article(4, "たいとる4", "執筆者4", "URL4"),
                Article(5, "たいとる5", "執筆者5", "URL5"),
                Article(6, "たいとる6", "執筆者6", "URL6"),
                Article(7, "たいとる7", "執筆者7", "URL7"),
                Article(8, "たいとる8", "執筆者8", "URL8"),
                Article(9, "たいとる9", "執筆者9", "URL9"),
                Article(10, "たいとる10", "執筆者10", "URL10")
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
}
