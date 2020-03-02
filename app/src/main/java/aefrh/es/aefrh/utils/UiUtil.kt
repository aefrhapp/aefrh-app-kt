package aefrh.es.aefrh.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object UiUtil {

    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?, placeholder: Drawable? = null) {
        Glide.with(view.context)
            .load(imageUrl)
            .error(placeholder)
            .into(view)
    }

}