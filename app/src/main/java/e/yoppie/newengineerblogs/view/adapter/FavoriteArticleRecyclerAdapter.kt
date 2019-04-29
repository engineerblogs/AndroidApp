package e.yoppie.newengineerblogs.view.adapter

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.FavoriteArticleItemBinding
import e.yoppie.newengineerblogs.listener.OnRecyclerListener
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.service.DiffArticleCallback
import e.yoppie.newengineerblogs.view.viewHolder.ArticleViewHolder
import e.yoppie.newengineerblogs.viewmodel.ArticleItemViewModel
import e.yoppie.newengineerblogs.viewmodel.FavoriteArticleViewModel

class FavoriteArticleRecyclerAdapter(private val context: Fragment, viewModel: FavoriteArticleViewModel, private var onRecyclerListener: OnRecyclerListener) : RecyclerView.Adapter<ArticleViewHolder>() {
    private var items: MutableList<Article> = mutableListOf()

    init {
        viewModel.favoriteArticleListLiveData.observe({ context.lifecycle }, { it?.apply { update(this) } })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FavoriteArticleItemBinding>(layoutInflater, R.layout.favorite_article_item, parent, false)
        binding.lifecycleOwner = context
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val favoriteArticleItemViewModel = ArticleItemViewModel()
        favoriteArticleItemViewModel.setArticle(items[position])
        holder.binding.apply {
            setVariable(BR.favoriteArticleItemViewModel, favoriteArticleItemViewModel)
            executePendingBindings()
        }
        holder.itemView.setOnClickListener {
            onRecyclerListener.onRecyclerViewClick(items[position].url, items[position].title)
        }
    }

    override fun getItemCount() = items.size

    private fun update(articleList: MutableList<Article>) {
        val diff = DiffUtil.calculateDiff(DiffArticleCallback(items, articleList))
        diff.dispatchUpdatesTo(this)
        this.items.clear()
        this.items.addAll(articleList)
    }
}