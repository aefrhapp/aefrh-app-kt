package aefrh.es.aefrh.utils

import aefrh.es.aefrh.R
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import timber.log.Timber

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun getEpocaIcon(epoca: String): Int {
    return when (epoca) {
        "Ibero-Romanas" -> R.drawable.ic_romana30
        "Medievales" -> R.drawable.ic_medieval30
        "Renacentistas" -> R.drawable.ic_renacentista30
        "Barrocas" -> R.drawable.ic_barroco30
        else -> R.drawable.ic_napoleonica30
    }
}

fun Context.goToBrowser(url: String?) {
    val intentWeb = Intent(Intent.ACTION_VIEW)
    intentWeb.addCategory(Intent.CATEGORY_BROWSABLE)
    intentWeb.data = Uri.parse(url)
    startActivity(intentWeb)
}

fun Context.sendEmail(recipient: String) {

    val mIntent = Intent(Intent.ACTION_SEND)
    mIntent.data = Uri.parse("mailto:")
    mIntent.type = "text/plain"
    mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
    mIntent.putExtra(Intent.EXTRA_SUBJECT, "[AEFRH][APP] SOLICITUD DE INFORMACIÓN")

    try {
        startActivity(Intent.createChooser(mIntent, "Enviar correo..."))
    } catch (e: Exception){
        Timber.e(e)
        Toast.makeText(this, getString(R.string.no_email_instalado), Toast.LENGTH_SHORT).show()
    }

}

fun Context.makePhoneCall(number: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@BindingAdapter("loadDrawable")
fun AppCompatButton.loadDrawable(@DrawableRes drawableInt: Int) {
    val drawable = context.getDrawable(drawableInt)
    drawable?.setTint(ContextCompat.getColor(context, android.R.color.white))
    setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}