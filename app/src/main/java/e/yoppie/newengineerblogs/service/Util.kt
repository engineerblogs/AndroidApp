package e.yoppie.newengineerblogs.service

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

object Util {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {
        Picasso.get().load(url).into(this)
    }
}