package e.yoppie.newengineerblogs.view.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.di.diInterface.DaggerAllArticleFragmentComponent
import e.yoppie.newengineerblogs.di.diModule.AllArticleFragmentModule
import e.yoppie.newengineerblogs.view.adapter.CategoryFragmentPagerAdapter
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel
import kotlinx.android.synthetic.main.all_article_fragment.*
import javax.inject.Inject

class AllArticleFragment : Fragment() {

    @Inject
    lateinit var viewModel: CompanyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val allArticleFragmentComponent = DaggerAllArticleFragmentComponent
                .builder()
                .allArticleFragmentModule(AllArticleFragmentModule(activity))
                .build()
        allArticleFragmentComponent.inject(this)
        this.viewModel.isLoad = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.all_article_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (this.viewModel.isLoad) {
            this.viewModel.loadAllArticles { setBinding() }
        } else {
            setBinding()
        }
    }

    private fun setBinding() {
        homeViewPager.offscreenPageLimit = 5
        val adapter = CategoryFragmentPagerAdapter(childFragmentManager, this.viewModel)
        homeViewPager.adapter = adapter
        homeTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        homeTabLayout.setupWithViewPager(homeViewPager)
    }
}