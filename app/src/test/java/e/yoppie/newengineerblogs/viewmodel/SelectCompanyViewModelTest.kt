package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import e.yoppie.newengineerblogs.model.data.Company
import org.junit.Before
import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SelectCompanyViewModelTest {
    private lateinit var selectCompanyViewModel: SelectCompanyViewModel

    @Before
    fun setUp() {
        selectCompanyViewModel = SelectCompanyViewModel()
    }

    @Test
    fun getCompanyListData() {
        val companyList = mutableListOf(
                Company("1", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg")
        )
        val mutableLiveData = MutableLiveData<MutableList<Company>>()
        mutableLiveData.value = companyList

        assertThat(selectCompanyViewModel.companyListData.value).isEqualTo(mutableLiveData.value)
    }

    @Test
    fun getCompanyList() {
        val companyList = mutableListOf(
                Company("1", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg")
        )

        assertThat(selectCompanyViewModel.companyList).isEqualTo(companyList)
    }

    @Test
    fun loadMoreCompanyList() {
        selectCompanyViewModel.loadMoreCompanyList()
        val companyList = mutableListOf(
                Company("1", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg"),
                Company("10", "ぐるなび", "https://images-na.ssl-images-amazon.com/images/I/61DAfypzYnL._SY445_.jpg")
        )
        val mutableLiveData = MutableLiveData<MutableList<Company>>()
        mutableLiveData.value = companyList
        assertThat(selectCompanyViewModel.companyListData.value).isEqualTo(mutableLiveData.value)
        assertThat(selectCompanyViewModel.companyList).isEqualTo(companyList)

        selectCompanyViewModel.loadMoreCompanyList()
        assertThat(selectCompanyViewModel.companyListData.value).isEqualTo(mutableLiveData.value)
        assertThat(selectCompanyViewModel.companyList).isEqualTo(companyList)
    }
}