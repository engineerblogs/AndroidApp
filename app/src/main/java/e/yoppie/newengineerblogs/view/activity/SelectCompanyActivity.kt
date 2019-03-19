package e.yoppie.newengineerblogs.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.ActivitySelectCompanyBinding
import e.yoppie.newengineerblogs.listener.OnCompanyRecyclerListener
import e.yoppie.newengineerblogs.model.room.AppDatabase
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity
import e.yoppie.newengineerblogs.view.adapter.CompanyRecyclerAdapter
import e.yoppie.newengineerblogs.viewmodel.SelectCompanyViewModel
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class SelectCompanyActivity : AppCompatActivity(), OnCompanyRecyclerListener {
    private var companyIdList: MutableList<String> = mutableListOf()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivitySelectCompanyBinding>(this, R.layout.activity_select_company)
        val viewModel = ViewModelProviders.of(this).get(SelectCompanyViewModel::class.java)
        binding.viewModel = viewModel

        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.companyRecyclerView.layoutManager = gridLayoutManager
        binding.companyRecyclerView.adapter = CompanyRecyclerAdapter(this, viewModel, this)

        binding.demoButton
                .clicks()
                .filter { companyIdList.size > 0 }
                .subscribe {
                    Log.d("yoshiya_debug", "click!!")
                    val companyEntity = CompanyEntity.create("01")
                    val db = AppDatabase.getInstance(applicationContext)!!
                    Completable
                            .fromAction { val id = db.companyDao().insert(companyEntity) }
                            .subscribeOn(Schedulers.io())
                            .subscribe()
                }

        binding.companyRecyclerView
                .scrollEvents()
                .filter { gridLayoutManager.itemCount - 1 <= gridLayoutManager.findLastVisibleItemPosition() }
                .subscribe { viewModel.loadMore() }

        //viewModel.load()
    }

    override fun onRecyclerViewClick(companyId: String) {
        if (companyIdList.contains(companyId)) {
            companyIdList.remove(companyId)
        } else {
            companyIdList.add(companyId)
        }
    }
}
