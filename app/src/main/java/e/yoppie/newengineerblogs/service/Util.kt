package e.yoppie.newengineerblogs.service

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

object Util {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {
        if(!url.isNullOrBlank()) {
            Glide.with(this.context).load(url).into(this)
        }
    }
}