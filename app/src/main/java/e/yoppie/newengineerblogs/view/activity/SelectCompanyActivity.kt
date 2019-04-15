package e.yoppie.newengineerblogs.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.facebook.stetho.Stetho
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.ActivitySelectCompanyBinding
import e.yoppie.newengineerblogs.listener.OnCompanyRecyclerListener
import e.yoppie.newengineerblogs.view.adapter.CompanyRecyclerAdapter
import e.yoppie.newengineerblogs.viewmodel.SelectCompanyViewModel

class SelectCompanyActivity : AppCompatActivity(), OnCompanyRecyclerListener {
    private var companyIdList: MutableList<String> = mutableListOf()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStetho()

        val binding = DataBindingUtil.setContentView<ActivitySelectCompanyBinding>(this, R.layout.activity_select_company)
        val viewModel = ViewModelProviders.of(this).get(SelectCompanyViewModel::class.java)
        binding.viewModel = viewModel

        val gridLayoutManager = LinearLayoutManager(this)
        binding.companyRecyclerView.layoutManager = gridLayoutManager
        binding.companyRecyclerView.adapter = CompanyRecyclerAdapter(this, viewModel, this)

        binding.companySelectButton
                .clicks()
                .filter { companyIdList.size > 0 }
                .subscribe {
                     viewModel.saveSelectCompanyList(companyIdList, this)
                }

        binding.companyRecyclerView
                .scrollEvents()
                .filter { gridLayoutManager.itemCount - 1 <= gridLayoutManager.findLastVisibleItemPosition() }
                .subscribe { viewModel.loadMoreCompanyList() }

        viewModel.loadFirstCompanyList(binding.selectProgressbar)
    }

    override fun onRecyclerViewClick(companyId: String) {
        if (companyIdList.contains(companyId)) {
            companyIdList.remove(companyId)
        } else {
            companyIdList.add(companyId)
        }
    }

    private fun initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())
    }
}
