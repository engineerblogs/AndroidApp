package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import androidx.navigation.NavController
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.model.data.Article

class ArticleViewModel : ViewModel() {
    lateinit var articleList: MutableLiveData<List<Article>>

    fun setArticleList(articleList: List<Article>) {
        val mutableLiveData = MutableLiveData<List<Article>>()
        mutableLiveData.value = articleList
        this.articleList = mutableLiveData
    }

    fun loadArticles() {
        val articles = listOf(
                Article(11, "たいとる11", "執筆者11", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(12, "たいとる12", "執筆者12", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(13, "たいとる13", "執筆者13", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(14, "たいとる14", "執筆者14", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(15, "たいとる15", "執筆者15", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(16, "たいとる16", "執筆者16", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(17, "たいとる17", "執筆者17", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(18, "たいとる18", "執筆者18", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(19, "たいとる19", "執筆者19", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(20, "たいとる20", "執筆者20", "https://developers.gnavi.co.jp/entry/slim-framework/")
        )
        setArticleList(articles)
    }
}
