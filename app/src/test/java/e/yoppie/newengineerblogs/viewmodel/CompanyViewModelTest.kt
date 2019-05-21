package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.model.data.Category
import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CompanyViewModelTest {
    private lateinit var companyViewModel: CompanyViewModel

    @Before
    fun setUp() {
        companyViewModel = CompanyViewModel()
    }

    @Test
    fun getCategoryListData() {
        val mutableLiveData = MutableLiveData<MutableList<Category>>()
        val articles = mutableListOf(
                Article("100", "title", "thumbnail", "url", "publishedDate", "author")
        )
        mutableLiveData.value = mutableListOf(
                Category("yoppie", "100", articles)
        )

        assertThat(companyViewModel.categoryListData.value).isEqualTo(mutableLiveData.value)
    }
}