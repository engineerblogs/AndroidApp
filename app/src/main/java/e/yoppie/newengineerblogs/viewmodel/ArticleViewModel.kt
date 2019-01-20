package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newengineerblogs.model.data.Article

class ArticleViewModel : ViewModel(){
    var articleList: MutableLiveData<List<Article>>? = null

    fun setArticleList(articleList: List<Article>){
        val mutableLiveData = MutableLiveData<List<Article>>()
        mutableLiveData.value = articleList
        this.articleList = mutableLiveData
    }

    fun loadArticles(){
        val articles = listOf(
                Article(11, "たいとる11", "執筆者11", "URL11"),
                Article(12, "たいとる12", "執筆者12", "URL12"),
                Article(13, "たいとる13", "執筆者13", "URL13"),
                Article(14, "たいとる14", "執筆者14", "URL14"),
                Article(15, "たいとる15", "執筆者15", "URL15"),
                Article(16, "たいとる16", "執筆者16", "URL16"),
                Article(17, "たいとる17", "執筆者17", "URL17"),
                Article(18, "たいとる18", "執筆者18", "URL18"),
                Article(19, "たいとる19", "執筆者19", "URL19"),
                Article(20, "たいとる20", "執筆者20", "URL20")
        )
        setArticleList(articles)
    }
}
