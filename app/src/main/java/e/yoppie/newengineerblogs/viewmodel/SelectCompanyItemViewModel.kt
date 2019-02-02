package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newengineerblogs.model.data.Company

class SelectCompanyItemViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()

    private val item = MutableLiveData<Company>().apply {
        this.observeForever {
            it?.apply {
                this@SelectCompanyItemViewModel.name.postValue(this.name)
                this@SelectCompanyItemViewModel.imageUrl.postValue(this.imageUrl)
            }
        }
    }

    fun setCompany(company: Company) {
        item.postValue(company)
    }
}