package e.yoppie.newengineerblogs.view.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.view.viewHolder.ArticleViewHolder

class ArticleRecyclerAdapter(private val articles: ArrayList<String>) : RecyclerView.Adapter<ArticleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.article_item, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

}