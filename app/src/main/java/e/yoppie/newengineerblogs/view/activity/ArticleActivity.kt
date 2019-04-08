package e.yoppie.newengineerblogs.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.webkit.WebViewClient
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityArticleBinding>(this, R.layout.activity_article)

        Log.d("yoshiya_debug", intent.getStringExtra("title"))

        binding.articleWebView.webViewClient = WebViewClient()
        binding.articleWebView.loadUrl(intent.getStringExtra("url"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return true
    }
}
