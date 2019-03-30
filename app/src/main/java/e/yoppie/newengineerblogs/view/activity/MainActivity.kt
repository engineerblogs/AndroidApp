package e.yoppie.newengineerblogs.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.ActivityMainBinding
import e.yoppie.newengineerblogs.view.adapter.CategoryFragmentPagerAdapter
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(CompanyViewModel::class.java)

        viewModel.getSavedCompanyList(
                {
                    val intent = Intent(this, SelectCompanyActivity::class.java)
                    startActivity(intent)
                },
                this
        )

        binding.viewModel = viewModel
        binding.mainViewPager.offscreenPageLimit = 5
        val adapter = CategoryFragmentPagerAdapter(supportFragmentManager, viewModel)

        binding.mainViewPager.adapter = adapter
        binding.mainTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
    }
}
