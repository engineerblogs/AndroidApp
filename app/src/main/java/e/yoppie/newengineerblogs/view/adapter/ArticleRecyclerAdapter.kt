package e.yoppie.newengineerblogs.view.adapter

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.ArticleItemBinding
import e.yoppie.newengineerblogs.listener.OnRecyclerListener
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.view.viewHolder.ArticleViewHolder
import e.yoppie.newengineerblogs.viewmodel.ArticleItemViewModel
import e.yoppie.newengineerblogs.viewmodel.ArticleViewModel

class ArticleRecyclerAdapter(private val context: Fragment, viewModel: ArticleViewModel, private var onRecyclerListener: OnRecyclerListener) : RecyclerView.Adapter<ArticleViewHolder>() {
    private var items: MutableList<Article> = mutableListOf()

    init {
        viewModel.articleListLiveData.observe({ context.lifecycle }, { it?.apply { update(this) } })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ArticleItemBinding>(layoutInflater, R.layout.article_item, parent, false)
        binding.lifecycleOwner = context
        return ArticleViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articleItemViewModel = ArticleItemViewModel()
        articleItemViewModel.setArticle(items[position])
        holder.binding.apply {
            setVariable(1, articleItemViewModel)
            executePendingBindings()
        }
        holder.itemView.setOnClickListener {
            onRecyclerListener.onRecyclerViewClick(items[position].url, items[position].title)
        }
    }

    private fun update(articleList: MutableList<Article>) {
        val diff = DiffUtil.calculateDiff(DiffCallback(items, articleList))
        diff.dispatchUpdatesTo(this)
        this.items.clear()
        this.items.addAll(articleList)
    }

    class DiffCallback(private val oldList: MutableList<Article>, private val newList: MutableList<Article>) : DiffUtil.Callback() {
        override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == (newList[newPosition])

        override fun areItemsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition].id == (newList[newPosition]).id

        override fun getNewListSize() = newList.size

        override fun getOldListSize() = oldList.size
    }

}