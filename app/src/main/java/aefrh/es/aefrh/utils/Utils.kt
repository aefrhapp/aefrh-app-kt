package aefrh.es.aefrh.utils

import aefrh.es.aefrh.R
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.widget.Toast
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

fun goToBrowser(url: String?, context: Context) {
    val intentWeb = Intent(Intent.ACTION_VIEW)
    intentWeb.addCategory(Intent.CATEGORY_BROWSABLE)
    intentWeb.data = Uri.parse(url)
    context.startActivity(intentWeb)
}

fun sendEmail(recipient: String, context: Context) {

    val mIntent = Intent(Intent.ACTION_SEND)
    mIntent.data = Uri.parse("mailto:")
    mIntent.type = "text/plain"
    mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
    mIntent.putExtra(Intent.EXTRA_SUBJECT, "[APP ANDROID] SOLICITUD DE INFORMACIÓN")

    try {
        context.startActivity(Intent.createChooser(mIntent, "Enviar correo..."))
    } catch (e: Exception){
        Timber.e(e)
        Toast.makeText(context, "Lo siento, no hay gestores de correo instalados.", Toast.LENGTH_SHORT).show()
    }

}