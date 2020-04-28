package aefrh.es.aefrh.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun ImageView.loadImage(imageUrl: String?, placeholder: Drawable? = null) {
    Glide.with(context)
        .load(imageUrl)
        .error(placeholder)
        .into(this)
}

@BindingAdapter("loadDrawable")
fun AppCompatButton.loadDrawable(@DrawableRes drawableInt: Int) {
    val drawable = context.getDrawable(drawableInt)
    drawable?.setTint(androidx.core.content.ContextCompat.getColor(context, android.R.color.white))
    setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}

@BindingAdapter(value = ["imageRounded", "placeholder"], requireAll = false)
fun ImageView.setImageRounded(url: String?, placeholder: Drawable? = null) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .error(placeholder)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(24)))
        .into(this)
}
