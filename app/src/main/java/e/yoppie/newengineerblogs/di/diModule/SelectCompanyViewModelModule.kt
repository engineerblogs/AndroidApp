package e.yoppie.newengineerblogs.di.diModule

import android.arch.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import e.yoppie.newengineerblogs.model.data.Company
import javax.inject.Singleton

@Module
class SelectCompanyViewModelModule {

    private val companyList = mutableListOf(
            Company("1", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg")
    )

    @Singleton
    @Provides
    fun provideCompanyList(): MutableList<Company> {
        return companyList
    }

    @Singleton
    @Provides
    fun provideCompanyListData(): MutableLiveData<MutableList<Company>> {
        val mutableLiveData = MutableLiveData<MutableList<Company>>()
        mutableLiveData.value = companyList
        return mutableLiveData
    }
}