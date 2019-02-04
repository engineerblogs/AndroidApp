package e.yoppie.newengineerblogs.service

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageUrl")
fun ImageView.imageUrl(url: String) {
    Picasso.get().load(url).into(this)
}
