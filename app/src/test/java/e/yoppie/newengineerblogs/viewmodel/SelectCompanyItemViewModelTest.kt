package e.yoppie.newengineerblogs.viewmodel

import e.yoppie.newengineerblogs.model.data.Company
import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SelectCompanyItemViewModelTest {

    @Test
    fun setCompany() {
        val selectCompanyItemViewModel = SelectCompanyItemViewModel()
        val company = Company("id", "name", "img")
        selectCompanyItemViewModel.setCompany(company)
        assertThat(selectCompanyItemViewModel.name.value).isEqualTo(company.name)
        assertThat(selectCompanyItemViewModel.img.value).isEqualTo(company.img)
    }
}