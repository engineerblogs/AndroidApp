package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newengineerblogs.model.data.Company
import e.yoppie.newengineerblogs.repository.CompanyRepository

class SelectCompanyViewModel : ViewModel() {
    var companyListData: MutableLiveData<MutableList<Company>>
    var companyList: MutableList<Company>

    init {
        val mutableLiveData = MutableLiveData<MutableList<Company>>()
        // todo: サンプル画像などに変更
        val companyList = mutableListOf(
                Company("1", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg"),
                Company("2", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg"),
                Company("3", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg"),
                Company("4", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg"),
                Company("5", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg"),
                Company("6", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg"),
                Company("7", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg"),
                Company("8", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg"),
                Company("9", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg")
        )
        mutableLiveData.value = companyList
        this.companyList = companyList
        companyListData = mutableLiveData
    }

    fun load() {
        val companyRepository = CompanyRepository()
        val newCompanyList = companyRepository.getCompanies()
    }

    fun loadMore() {
        val company = Company("10", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg")
        if (this.companyList.last().id != company.id) {
            this.companyList.add(company)
            this.companyListData.postValue(this.companyList)
        }
    }
}