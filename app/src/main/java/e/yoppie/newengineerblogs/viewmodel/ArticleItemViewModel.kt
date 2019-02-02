package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newengineerblogs.model.data.Article

class ArticleItemViewModel : ViewModel() {
    val title = MutableLiveData<String>()
    val author = MutableLiveData<String>()

    private val item = MutableLiveData<Article>().apply {
        this.observeForever {
            it?.apply {
                this@ArticleItemViewModel.title.postValue(this.title)
                this@ArticleItemViewModel.author.postValue(this.author)
            }
        }
    }

    fun setArticle(article: Article) {
        item.postValue(article)
    }
}