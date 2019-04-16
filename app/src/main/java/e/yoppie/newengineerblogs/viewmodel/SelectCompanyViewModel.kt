package e.yoppie.newengineerblogs.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ProgressBar
import e.yoppie.newengineerblogs.di.diInterface.DaggerSelectCompanyViewModelComponent
import e.yoppie.newengineerblogs.di.diModule.SelectCompanyViewModelModule
import e.yoppie.newengineerblogs.model.data.Company
import e.yoppie.newengineerblogs.repository.CompanyRepository
import e.yoppie.newengineerblogs.view.activity.HomeActivity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Suppress("DEPRECATION")
class SelectCompanyViewModel : ViewModel() {

    @Inject
    lateinit var companyListData: MutableLiveData<MutableList<Company>>

    @Inject
    lateinit var companyList: MutableList<Company>

    private var companyRepository: CompanyRepository = CompanyRepository()

    init {
        val selectCompanyViewModelComponent = DaggerSelectCompanyViewModelComponent
                .builder()
                .selectCompanyViewModelModule(SelectCompanyViewModelModule())
                .build()
        selectCompanyViewModelComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    fun loadFirstCompanyList(selectProgressbar: ProgressBar) {
        companyRepository.getCompanyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    this.companyList = res.companies
                    companyListData.postValue(res.companies)
                    selectProgressbar.visibility = ProgressBar.INVISIBLE
                }, { error ->
                    Log.d("yoshiya_debug", error.message)
                })
    }

    fun loadMoreCompanyList() {
        val company = Company("10", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg")
        if (this.companyList.last().id != company.id) {
            this.companyList.add(company)
            this.companyListData.postValue(this.companyList)
        }
    }

    @SuppressLint("CheckResult")
    fun saveSelectCompanyList(companyIdList: MutableList<String>, context: Context) {
        Completable
                .fromAction {
                    companyRepository.saveCompanyList(companyIdList, context)
                }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    val intent = Intent(context, HomeActivity::class.java)
                    context.startActivity(intent)
                }
    }
}