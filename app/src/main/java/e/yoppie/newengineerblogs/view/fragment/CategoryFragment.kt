package e.yoppie.newengineerblogs.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.CategoryFragmentBinding
import e.yoppie.newengineerblogs.view.adapter.ArticleRecyclerAdapter
import e.yoppie.newengineerblogs.viewmodel.ArticleViewModel
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel

class CategoryFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val position = arguments!!.getInt("position")
        val categoryViewModel = ViewModelProviders.of(activity!!).get(CompanyViewModel::class.java)
        val articleViewModel = ViewModelProviders.of(this).get(position.toString(), ArticleViewModel::class.java)
        articleViewModel.setArticleList(categoryViewModel.categoryList!!.value!![position].articleList)

        val binding = DataBindingUtil.inflate<CategoryFragmentBinding>(inflater, R.layout.category_fragment, container, false)

        binding.articleRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.articleRecyclerView.adapter = ArticleRecyclerAdapter(articleViewModel)

        return binding.root
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

}