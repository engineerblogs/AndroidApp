package e.yoppie.newengineerblogs.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.view.adapter.CategoryFragmentPagerAdapter
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel
import kotlinx.android.synthetic.main.all_article_fragment.*

class AllArticleFragment : Fragment() {

    private lateinit var viewModel: CompanyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ViewModelProviders.of(activity!!).get(CompanyViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.all_article_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel.loadAllArticles { setBinding() }
    }

    private fun setBinding() {
        homeViewPager.offscreenPageLimit = 5
        val adapter = CategoryFragmentPagerAdapter(childFragmentManager, this.viewModel)
        homeViewPager.adapter = adapter
        homeTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        homeTabLayout.setupWithViewPager(homeViewPager)
    }
}