package e.yoppie.newengineerblogs.service

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

object Util {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {
        if(!url.isNullOrBlank()) {
            Glide.with(this.context)
                    .load(url)
                    .error(android.R.drawable.btn_star_big_on)
                    .into(this)
        }
    }
}