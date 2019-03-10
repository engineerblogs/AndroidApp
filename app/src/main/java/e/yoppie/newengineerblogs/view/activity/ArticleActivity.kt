package e.yoppie.newengineerblogs.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import com.jakewharton.rxbinding2.support.v7.widget.itemClicks
import com.jakewharton.rxbinding2.support.v7.widget.navigationClicks
import e.yoppie.newengineerblogs.R

import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setSupportActionBar(toolbar)

        val url = intent.getStringExtra("url")
        articleWebView.webViewClient = WebViewClient()
        articleWebView.loadUrl(url)

        // rxbinding-appcompat-v7-kotlin
        toolbar.itemClicks().subscribe{ Log.d("yoshiya_debug", "itemClicks") }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return true
    }
}
