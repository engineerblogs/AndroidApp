package e.yoppie.newengineerblogs.view.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.service.Util
import kotlinx.android.synthetic.main.activity_article.*
import androidx.navigation.NavOptions

class ArticleActivity : AppCompatActivity() {

    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setSupportActionBar(articleToolBar)

        articleToolBar.title = intent.getStringExtra("title")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        articleWebView.webViewClient = WebViewClient()
        url = intent.getStringExtra("url")
        articleWebView.loadUrl(url)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.toolBarAddItem -> {
                Log.d("yoppie_debug", "toolBarAddItem")
            }
            R.id.toolBarUrlCopyItem -> {
                Util.clipBoardCopy(applicationContext, url)
                Snackbar.make(
                        findViewById(android.R.id.content),
                        "URLをコピーしました\n$url",
                        Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
