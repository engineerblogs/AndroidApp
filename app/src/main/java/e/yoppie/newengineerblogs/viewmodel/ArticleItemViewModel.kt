package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newengineerblogs.model.data.Article

class ArticleItemViewModel : ViewModel() {
    val id = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val thumbnail = MutableLiveData<String>()
    val url = MutableLiveData<String>()
    val publishedDate = MutableLiveData<String>()
    val author = MutableLiveData<String>()


    private val item = MutableLiveData<Article>().apply {
        this.observeForever {
            it?.apply {
                this@ArticleItemViewModel.id.postValue(this.id)
                this@ArticleItemViewModel.title.postValue(this.title)
                this@ArticleItemViewModel.thumbnail.postValue(this.thumbnail)
                this@ArticleItemViewModel.url.postValue(this.url)
                this@ArticleItemViewModel.publishedDate.postValue(this.publishedDate)
                this@ArticleItemViewModel.author.postValue(this.author)
            }
        }
    }

    fun setArticle(article: Article) {
        item.postValue(article)
    }
}