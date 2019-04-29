package e.yoppie.newengineerblogs.view.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.BookmarkFragmentBinding
import e.yoppie.newengineerblogs.databinding.CategoryFragmentBinding
import e.yoppie.newengineerblogs.listener.OnRecyclerListener
import e.yoppie.newengineerblogs.view.activity.ArticleActivity
import e.yoppie.newengineerblogs.view.adapter.FavoriteArticleRecyclerAdapter
import e.yoppie.newengineerblogs.viewmodel.ArticleViewModel
import e.yoppie.newengineerblogs.viewmodel.FavoriteArticleViewModel

class BookmarkFragment : Fragment(), OnRecyclerListener {

    lateinit var favoriteArticleViewModel: FavoriteArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteArticleViewModel = ViewModelProviders.of(this).get(FavoriteArticleViewModel::class.java)
        favoriteArticleViewModel.loadFirstFavoriteArticleList()
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<BookmarkFragmentBinding>(inflater, R.layout.bookmark_fragment, container, false)
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.favoriteArticleRecyclerView.layoutManager = linearLayoutManager
        binding.favoriteArticleRecyclerView.adapter = FavoriteArticleRecyclerAdapter(this, favoriteArticleViewModel, this)
        binding.favoriteArticleRecyclerView
                .scrollEvents()
                .filter { linearLayoutManager.itemCount - 1 <= linearLayoutManager.findLastVisibleItemPosition() }
                .subscribe {
                    //favoriteArticleViewModel.loadMore(companyId)
                }
        return binding.root
    }

    override fun onRecyclerViewClick(url: String, title: String) {
        val intent = Intent(this.context, ArticleActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("title", title)
        startActivity(intent)
    }
}