package e.yoppie.newengineerblogs.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import e.yoppie.newengineerblogs.view.fragment.CategoryFragment

class CategoryFragmentPagerAdapter(fm: FragmentManager?, items: ArrayList<String>) : FragmentPagerAdapter(fm) {

    private var items: ArrayList<String>? = null

    init {
        this.items = items
    }

    override fun getItem(position: Int): Fragment {
        return CategoryFragment()
    }

    override fun getCount(): Int {
        return items!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return items!![position]
    }

}