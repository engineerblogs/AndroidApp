package e.yoppie.newengineerblogs.service

import e.yoppie.newengineerblogs.model.data.Company
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.assertj.core.api.Assertions.*

@RunWith(RobolectricTestRunner::class)
class DiffCompanyCallbackTest {
    lateinit var diffCompanyCallback: DiffCompanyCallback

    private val oldCompanies = mutableListOf(
            Company("0", "name_0", "img_0"),
            Company("1", "name_1", "img_1"),
            Company("2", "name_2", "img_2")
    )

    private val newCompanies = mutableListOf(
            Company("3", "name_3", "img_3"),
            Company("4", "name_4", "img_4"),
            Company("2", "name_2", "img_2")
    )

    @Before
    fun setUp() {
        diffCompanyCallback = DiffCompanyCallback(oldCompanies, newCompanies)
    }

    @Test
    fun areContentsTheSame() {
        assertThat(diffCompanyCallback.areContentsTheSame(0, 1)).isFalse()
        assertThat(diffCompanyCallback.areContentsTheSame(2, 2)).isTrue()
    }

    @Test
    fun areItemsTheSame() {
        assertThat(diffCompanyCallback.areItemsTheSame(0, 1)).isFalse()
        assertThat(diffCompanyCallback.areItemsTheSame(2, 2)).isTrue()
    }

    @Test
    fun getNewListSize() {
        assertThat(diffCompanyCallback.newListSize).isEqualTo(3)
    }

    @Test
    fun getOldListSize() {
        assertThat(diffCompanyCallback.oldListSize).isEqualTo(3)
    }
}