package e.yoppie.newengineerblogs.view.viewHolder

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import e.yoppie.newengineerblogs.R

class CompanyViewHolder(val binding: ViewDataBinding, val view: View) : RecyclerView.ViewHolder(binding.root){
    val favoriteLottieAnimationView: LottieAnimationView = view.findViewById(R.id.favoriteLottieAnimationView)
}