package aefrh.es.core.utils

import aefrh.es.core.R
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    if(imageUrl != null)
        Glide.with(context)
            .load(imageUrl)
            .error(R.drawable.logo_espana)
            .into(this)
}

@BindingAdapter("loadDrawable")
fun AppCompatButton.loadDrawable(@DrawableRes drawableInt: Int?) {
    val drawable = drawableInt?.let { context.getDrawable(it) }
    drawable?.setTint(androidx.core.content.ContextCompat.getColor(context, android.R.color.white))
    setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}

@BindingAdapter("imageRounded")
fun ImageView.setImageRounded(imageUrl: String?) {
    if(imageUrl != null)
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(24)))
            .error(R.drawable.logo_espana)
            .into(this)
}