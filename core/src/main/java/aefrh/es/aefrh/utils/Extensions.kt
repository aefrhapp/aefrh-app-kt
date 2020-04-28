package aefrh.es.aefrh.utils

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.widget.Toast
import timber.log.Timber

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
        Toast.makeText(this, "Lo siento, no hay gestores de correo instalados.", Toast.LENGTH_SHORT).show()
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

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()