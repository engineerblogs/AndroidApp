package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ArticleViewModel : ViewModel(){
    var articleText: MutableLiveData<String> = MutableLiveData()


}