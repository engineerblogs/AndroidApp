package e.yoppie.newengineerblogs.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding.support.v7.widget.scrollEvents
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.CategoryFragmentBinding
import e.yoppie.newengineerblogs.listener.OnRecyclerListener
import e.yoppie.newengineerblogs.view.activity.ArticleActivity
import e.yoppie.newengineerblogs.view.adapter.ArticleRecyclerAdapter
import e.yoppie.newengineerblogs.viewmodel.ArticleViewModel
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel

class CategoryFragment : Fragment(), OnRecyclerListener {

    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val position = arguments!!.getInt("position")
        val categoryViewModel = ViewModelProviders.of(activity!!).get(CompanyViewModel::class.java)
        val articleViewModel = ViewModelProviders.of(this).get(position.toString(), ArticleViewModel::class.java)
        articleViewModel.set(categoryViewModel.categoryList.value!![position].articleList)
        this.articleViewModel = articleViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding = DataBindingUtil.inflate<CategoryFragmentBinding>(inflater, R.layout.category_fragment, container, false)
        binding.setLifecycleOwner(this)
        binding.categoryFragmentSwipeRefreshLayout.setOnRefreshListener {
            articleViewModel.loadArticles()
            if (binding.categoryFragmentSwipeRefreshLayout.isRefreshing) {
                binding.categoryFragmentSwipeRefreshLayout.isRefreshing = false
            }
        }
        binding = setArticleRecyclerView(binding)
        return binding.root
    }

    private fun setArticleRecyclerView(binding: CategoryFragmentBinding): CategoryFragmentBinding? {
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.articleRecyclerView.layoutManager = linearLayoutManager
        binding.articleRecyclerView.adapter = ArticleRecyclerAdapter(this, articleViewModel, this)
        binding.articleRecyclerView
                .scrollEvents()
                .filter { linearLayoutManager.itemCount - 1 <= linearLayoutManager.findLastVisibleItemPosition() }
                .subscribe{ articleViewModel.loadMore() }

        return binding
    }

    companion object {
        fun newInstance(position: Int): CategoryFragment {
            return CategoryFragment().apply {
                arguments = Bundle().apply {
                    putInt("position", position)
                }
            }
        }
    }

    override fun onRecyclerViewClick(url: String) {
        val intent = Intent(this.context, ArticleActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}
