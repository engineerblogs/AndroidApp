package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData

class FavoriteArticleItemViewModel {
    val id = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val thumbnail = MutableLiveData<String>()
    val url = MutableLiveData<String>()
    val publishedDate = MutableLiveData<String>()
    val author = MutableLiveData<String>()


}