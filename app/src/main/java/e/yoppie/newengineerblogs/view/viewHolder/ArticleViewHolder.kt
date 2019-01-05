package e.yoppie.newengineerblogs.view.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import e.yoppie.newengineerblogs.R

class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val articleTextView: TextView = view.findViewById(R.id.articleTextView)
}