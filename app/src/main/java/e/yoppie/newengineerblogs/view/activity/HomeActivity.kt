package e.yoppie.newengineerblogs.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.firebase.analytics.FirebaseAnalytics
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity
import e.yoppie.newengineerblogs.repository.ArticleRepository
import e.yoppie.newengineerblogs.service.Util
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.not_net_connection.*

class HomeActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        testFirebase()

        if (!Util.isNetConnection(this)) {
            setContentView(R.layout.not_net_connection)
            reloadNetworkButton
                    .clicks()
                    .subscribe { reload() }
            return
        }

        setContentView(R.layout.activity_home)

        val articleRepository = ArticleRepository()
        var localCompanyEntityList: List<CompanyEntity> = mutableListOf()
        Completable
                .fromAction {
                    localCompanyEntityList = articleRepository.getLocalSavedCompanyList(this)
                }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (localCompanyEntityList.isEmpty()) {
                        val intent = Intent(this, SelectCompanyActivity::class.java)
                        startActivity(intent)
                    } else {
                        val navController = findNavController(R.id.navHostFragment)
                        NavigationUI.setupWithNavController(bottomNavigation, navController)
                    }
                }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (Util.isNetConnection(this)) {
            menuInflater.inflate(R.menu.category_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.toolBarCategoryAddItem -> {
                val intent = Intent(this, SelectCompanyActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun reload() {
        overridePendingTransition(0, 0)
        val intent = intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()

        overridePendingTransition(0, 0)
        startActivity(intent)
    }

    private fun testFirebase(){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "01")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "content_type")
        firebaseAnalytics.setUserProperty("property_foo_bar", "baz")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}
