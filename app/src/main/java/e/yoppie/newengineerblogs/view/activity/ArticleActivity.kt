package e.yoppie.newengineerblogs.view.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebViewClient
import e.yoppie.newengineerblogs.R

import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setSupportActionBar(toolbar)

        val url = intent.getStringExtra("url")
        articleWebView.webViewClient = WebViewClient()
        articleWebView.loadUrl(url)
    }

}
