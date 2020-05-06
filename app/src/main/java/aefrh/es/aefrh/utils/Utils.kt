package aefrh.es.aefrh.utils

import aefrh.es.aefrh.R

fun getEpocaIcon(epoca: String): Int {
    return when (epoca) {
        "Ibero-Romanas" -> R.drawable.ic_romano
        "Medievales" -> R.drawable.ic_medieval
        "Renacentistas" -> R.drawable.ic_renacentista
        "Barrocas" -> R.drawable.ic_barroco
        else -> R.drawable.ic_napoleonico
    }
}