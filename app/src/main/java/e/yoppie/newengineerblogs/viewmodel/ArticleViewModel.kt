package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View

class ArticleViewModel(val articleTitle: String) : ViewModel(){
    fun onClick(view: View) {
        Log.d("debug", "on_click_test")
    }
}