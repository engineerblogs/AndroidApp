package e.yoppie.newengineerblogs.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.jakewharton.rxbinding.support.v7.widget.scrollEvents
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.ActivitySelectCompanyBinding
import e.yoppie.newengineerblogs.view.adapter.CompanyRecyclerAdapter
import e.yoppie.newengineerblogs.viewmodel.SelectCompanyViewModel

class SelectCompanyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivitySelectCompanyBinding>(this, R.layout.activity_select_company)
        val viewModel = ViewModelProviders.of(this).get(SelectCompanyViewModel::class.java)
        binding.viewModel = viewModel

        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.companyRecyclerView.layoutManager = gridLayoutManager
        binding.companyRecyclerView.adapter = CompanyRecyclerAdapter(this, viewModel)
        binding.companyRecyclerView
                .scrollEvents()
                .filter { gridLayoutManager.itemCount - 1 <= gridLayoutManager.findLastVisibleItemPosition() }
                .subscribe { viewModel.loadMore() }
    }
}