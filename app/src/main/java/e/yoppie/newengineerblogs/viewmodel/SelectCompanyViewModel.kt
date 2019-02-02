package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import e.yoppie.newengineerblogs.model.data.Company

class SelectCompanyViewModel : ViewModel() {
    var companyListData: MutableLiveData<MutableList<Company>>
    var companyList: MutableList<Company>

    init {
        val mutableLiveData = MutableLiveData<MutableList<Company>>()
        val companyList = mutableListOf(
                Company(1, "ぐるなび", "url"),
                Company(2, "ぐるなび", "url"),
                Company(3, "ぐるなび", "url"),
                Company(4, "ぐるなび", "url"),
                Company(5, "ぐるなび", "url"),
                Company(6, "ぐるなび", "url"),
                Company(7, "ぐるなび", "url"),
                Company(8, "ぐるなび", "url"),
                Company(9, "ぐるなび", "url")
        )
        mutableLiveData.value = companyList
        this.companyList = companyList
        companyListData = mutableLiveData
    }

    fun loadMore() {
        val company = Company(10, "ぐるなび", "url")
        if (this.companyList.last().id != company.id) {
            this.companyList.add(company)
            this.companyListData.postValue(this.companyList)
        }
    }
}