package e.yoppie.newengineerblogs.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
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

class ArticleRecyclerAdapter(private val context: AppCompatActivity, private val viewModel: ArticleViewModel, private var onRecyclerListener: OnRecyclerListener) : RecyclerView.Adapter<ArticleViewHolder>() {
    private lateinit var recyclerView: RecyclerView
    private var items: MutableList<Article> = arrayListOf()

    init {
        viewModel.articleList.observe({ context.lifecycle }, { it?.apply { update(this) } })
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ArticleItemBinding>(layoutInflater, R.layout.article_item, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articleItemViewModel = ArticleItemViewModel()
        articleItemViewModel.setArticle(items[position])
        holder.binding.apply {
            setVariable(2, articleItemViewModel)
            executePendingBindings()
        }
        holder.itemView.setOnClickListener {
            onRecyclerListener.onRecyclerViewClick(items[position].url)
        }
    }

    private fun update(articleList: List<Article>) {
        val diff = DiffUtil.calculateDiff(DiffCallback(items, articleList))
        diff.dispatchUpdatesTo(this)
        this.items.clear()
        this.items.addAll(articleList)
    }

    class DiffCallback(private val oldList: List<Article>, private val newList: List<Article>): DiffUtil.Callback(){
        override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == (newList[newPosition])

        override fun areItemsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition].id == (newList[newPosition]).id

        override fun getNewListSize() = newList.size

        override fun getOldListSize() = oldList.size
    }

}