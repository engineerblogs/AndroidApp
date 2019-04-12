package e.yoppie.newengineerblogs.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.view.fragment.AllArticleFragment
import e.yoppie.newengineerblogs.view.fragment.BookmarkFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.allArticleItem -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, AllArticleFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.bookmarkItem -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, BookmarkFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // ここでSelectCompanyActivityの判定する
        // データなかったらSelectCompanyActivity
        // データあったら以下
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AllArticleFragment())
                .commit()
    }
}
