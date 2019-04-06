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
        val viewModel = setBinding(binding)

        viewModel.getSavedCompanyList(
                {
                    val intent = Intent(this, SelectCompanyActivity::class.java)
                    startActivity(intent)
                },
                { setBinding(binding) },
                this
        )
    }

    private fun setBinding(binding: ActivityMainBinding): CompanyViewModel {
        val viewModel = ViewModelProviders.of(this).get(CompanyViewModel::class.java)
        binding.viewModel = viewModel
        binding.mainViewPager.offscreenPageLimit = 5
        val adapter = CategoryFragmentPagerAdapter(supportFragmentManager, viewModel)
        adapter.destroyTarget(binding.mainViewPager, 0)
        binding.mainViewPager.adapter = adapter
        binding.mainTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)

        return viewModel
    }
}
