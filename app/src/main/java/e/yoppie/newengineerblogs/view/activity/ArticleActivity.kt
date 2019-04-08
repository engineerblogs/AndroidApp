package e.yoppie.newengineerblogs.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.webkit.WebViewClient
import e.yoppie.newengineerblogs.R
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setSupportActionBar(articleToolBar)
        articleToolBar.title = intent.getStringExtra("title")

        articleWebView.webViewClient = WebViewClient()
        articleWebView.loadUrl(intent.getStringExtra("url"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return true
    }

}
