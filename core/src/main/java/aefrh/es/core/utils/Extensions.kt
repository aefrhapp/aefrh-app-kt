package aefrh.es.core.utils

import aefrh.es.core.domain.Noticia
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

fun Context.shareNoticia(title: String, link: String) {

    val mIntent = Intent(Intent.ACTION_SEND)
    mIntent.type = "text/plain"
    mIntent.putExtra(Intent.EXTRA_SUBJECT, "[AEFRH][APP] Te comparte esta noticia")
    mIntent.putExtra(Intent.EXTRA_TEXT, """$title $link""")

    try {
        startActivity(Intent.createChooser(mIntent, "Compartir..."))
    } catch (e: Exception){
        Timber.e(e)
        Toast.makeText(this, "Lo siento, ha ocurrido un error.", Toast.LENGTH_SHORT).show()
    }

}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Noticia.reformatNoticia(): String {
    return String.format(
        styleHtml,
        this.title,
        this.getSingleDate(),
        this.content
    )
}

const val styleHtml =
    """
        <head>
        	<link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
            <style>
        		body{margin: 24px; text-align:left; font-size:15px; font-family:"Poppins"; color:#444444;}
                h1{font-size:22px;}
                h2, h3, h6{font-size:15px;}
                img{display: block; max-width: 100%%;}
                a{color:#001A46;}
            </style>
        </head>
        <body>
            <h1>%1${'$'}s</h1>
            <h2>%2${'$'}s</h2>
            <br>%3${'$'}s</br>
        </body>
    """