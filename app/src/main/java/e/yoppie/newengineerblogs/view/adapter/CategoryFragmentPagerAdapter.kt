package e.yoppie.newengineerblogs.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.ViewGroup
import e.yoppie.newengineerblogs.model.data.Category
import e.yoppie.newengineerblogs.view.fragment.CategoryFragment
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel

class CategoryFragmentPagerAdapter(fm: FragmentManager?, viewModel: CompanyViewModel) : FragmentPagerAdapter(fm) {

    private var categoryList: List<Category>? = viewModel.categoryListData.value

    override fun getItem(position: Int): Fragment {
        Log.d("yoshiya_debug", "adapter_item/$position")
        return CategoryFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return categoryList!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        Log.d("yoshiya_debug", "adapter_item2/$position")
        Log.d("yoshiya_debug", "adapter_title/" + categoryList!![position].name)
        return categoryList!![position].name
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    fun destroyTarget(pager: ViewPager, position: Int) {
        if (position < count) {
            val target = this.instantiateItem(pager, position)
            destroyItem(pager, position, target)
        }
    }
}