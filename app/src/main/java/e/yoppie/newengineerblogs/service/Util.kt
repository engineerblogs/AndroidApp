package e.yoppie.newengineerblogs.service

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso

object Util {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {
        if(!url.isNullOrBlank()) {
            Log.d("yoshiya_debug", "loadImage")
            Log.d("yoshiya_debug", url)
//            Picasso.get().load(url).into(imageView)
            Picasso.get().load(url).into(this)
        }
    }
}