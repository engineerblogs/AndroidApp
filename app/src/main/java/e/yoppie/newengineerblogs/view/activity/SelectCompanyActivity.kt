package e.yoppie.newengineerblogs.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.jakewharton.rxbinding2.support.design.widget.RxFloatingActionButton
import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView
import com.jakewharton.rxbinding2.support.design.widget.itemSelections
import com.jakewharton.rxbinding2.support.v17.leanback.widget.RxSearchEditText
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.ActivitySelectCompanyBinding
import e.yoppie.newengineerblogs.view.adapter.CompanyRecyclerAdapter
import e.yoppie.newengineerblogs.viewmodel.SelectCompanyViewModel

class SelectCompanyActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivitySelectCompanyBinding>(this, R.layout.activity_select_company)
        val viewModel = ViewModelProviders.of(this).get(SelectCompanyViewModel::class.java)
        binding.viewModel = viewModel

        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.companyRecyclerView.layoutManager = gridLayoutManager
        binding.companyRecyclerView.adapter = CompanyRecyclerAdapter(this, viewModel)

        // rxbinding2:rxbinding-kotlin
        binding.demoButton
                .clicks()
                .subscribe { Log.d("yoshiya_debug", "click!!") }

        // rxbinding2:rxbinding-recyclerview-v7-kotlin
        binding.companyRecyclerView
                .scrollEvents()
                .filter { gridLayoutManager.itemCount - 1 <= gridLayoutManager.findLastVisibleItemPosition() }
                .subscribe { viewModel.loadMore() }

        // rxbinding2:rxbinding-design-kotlin
        binding.demoNavigationView.itemSelections().subscribe { Log.d("yoshiya_debug", "itemSelections") }

//        viewModel.load()
    }
}
