package e.yoppie.newengineerblogs.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import e.yoppie.newengineerblogs.model.data.Category
import e.yoppie.newengineerblogs.view.fragment.CategoryFragment
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel

class CategoryFragmentPagerAdapter(fm: FragmentManager?, viewModel: CompanyViewModel) : FragmentStatePagerAdapter(fm) {

    private var categoryList: List<Category>? = viewModel.categoryListData.value

    override fun getItem(position: Int): Fragment = CategoryFragment.newInstance(position)

    override fun getCount(): Int = categoryList!!.count()

    override fun getPageTitle(position: Int): CharSequence? = categoryList!![position].name
}