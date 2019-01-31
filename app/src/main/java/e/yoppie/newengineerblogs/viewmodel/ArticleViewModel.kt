package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import e.yoppie.newengineerblogs.model.data.Article

class ArticleViewModel : ViewModel() {
    lateinit var articleListLiveData: MutableLiveData<MutableList<Article>>
    private var articleList: MutableList<Article> = mutableListOf()

    fun set(articleList: MutableList<Article>) {
        val mutableLiveData = MutableLiveData<MutableList<Article>>()
        mutableLiveData.value = articleList
        this.articleList = articleList
        this.articleListLiveData = mutableLiveData
    }

    fun loadArticles() {
        val articleList = mutableListOf(
                Article(11, "たいとる11", "執筆者11", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(12, "たいとる12", "執筆者12", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(13, "たいとる13", "執筆者13", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(14, "たいとる14", "執筆者14", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(15, "たいとる15", "執筆者15", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(16, "たいとる16", "執筆者16", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(17, "たいとる17", "執筆者17", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(18, "たいとる18", "執筆者18", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(19, "たいとる19", "執筆者19", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(20, "たいとる20", "執筆者20", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(21, "たいとる21", "執筆者21", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(22, "たいとる22", "執筆者22", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(23, "たいとる23", "執筆者23", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(24, "たいとる24", "執筆者24", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(25, "たいとる25", "執筆者25", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(26, "たいとる26", "執筆者26", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(27, "たいとる27", "執筆者27", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(28, "たいとる28", "執筆者28", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(29, "たいとる29", "執筆者29", "https://developers.gnavi.co.jp/entry/slim-framework/"),
                Article(30, "たいとる30", "執筆者30", "https://developers.gnavi.co.jp/entry/slim-framework/")
        )
        this.articleList = articleList
        this.articleListLiveData.postValue(articleList)
    }

    fun loadMore() {
        val article = Article(31, "たいとる31", "執筆者31", "https://developers.gnavi.co.jp/entry/slim-framework/")
        if(this.articleList.last().id != article.id){
            this.articleList.add(article)
            this.articleListLiveData.postValue(this.articleList)
        }
    }
}
