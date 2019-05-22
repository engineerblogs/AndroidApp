package e.yoppie.newengineerblogs.view.adapter

import android.support.v4.app.FragmentManager
import com.nhaarman.mockitokotlin2.mock
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel
import org.junit.Before
import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CategoryFragmentPagerAdapterTest {
    private lateinit var categoryFragmentPagerAdapter: CategoryFragmentPagerAdapter

    @Before
    fun setUp() {
        val fragmentManager: FragmentManager = mock(name = "FragmentManager")
        categoryFragmentPagerAdapter = CategoryFragmentPagerAdapter(fragmentManager, CompanyViewModel())
    }

    @Test
    fun getItem() {
        val position = 0
        val argumentPosition = categoryFragmentPagerAdapter.getItem(position).arguments!!.getInt("position")
        assertThat(argumentPosition).isEqualTo(position)
    }

    @Test
    fun getCount() {
        assertThat(categoryFragmentPagerAdapter.count).isEqualTo(1)
    }

    @Test
    fun getPageTitle() {
        assertThat(categoryFragmentPagerAdapter.getPageTitle(0)).isEqualTo("yoppie")
    }
}