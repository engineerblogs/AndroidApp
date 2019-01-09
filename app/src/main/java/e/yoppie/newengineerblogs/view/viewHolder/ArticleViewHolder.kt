package e.yoppie.newengineerblogs.view.viewHolder

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import e.yoppie.newengineerblogs.viewmodel.ArticleViewModel

class ArticleViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(articleViewModel: ArticleViewModel){
        binding.setVariable(1, articleViewModel)
        binding.executePendingBindings()
    }
}