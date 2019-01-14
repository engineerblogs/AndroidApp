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
}
