package e.yoppie.newengineerblogs.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.ArticleItemBinding
import e.yoppie.newengineerblogs.view.viewHolder.ArticleViewHolder
import e.yoppie.newengineerblogs.viewmodel.ArticleViewModel

class ArticleRecyclerAdapter(private val viewModel: ArticleViewModel) : RecyclerView.Adapter<ArticleViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ArticleItemBinding>(layoutInflater, R.layout.article_item, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModel.articleList!!.value!!.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(viewModel.articleList!!.value!![position])
    }

}