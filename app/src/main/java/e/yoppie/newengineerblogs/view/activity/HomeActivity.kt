package e.yoppie.newengineerblogs.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity
import e.yoppie.newengineerblogs.repository.ArticleRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_home)

        val articleRepository = ArticleRepository()
        var localCompanyEntitiyList: List<CompanyEntity> = mutableListOf()
        Completable
                .fromAction {
                    localCompanyEntitiyList = articleRepository.getLocalSavedCompanyList(this)
                }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (localCompanyEntitiyList.isEmpty()) {
                        val intent = Intent(this, SelectCompanyActivity::class.java)
                        startActivity(intent)
                    } else {
                        val navController = findNavController(R.id.navHostFragment)
                        NavigationUI.setupWithNavController(bottomNavigation, navController)
                    }
                }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.category_menu, menu)
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
}
