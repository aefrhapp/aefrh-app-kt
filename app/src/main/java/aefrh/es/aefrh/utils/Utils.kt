package aefrh.es.aefrh.utils

import aefrh.es.aefrh.R

fun getEpocaIcon(epoca: String): Int {
    return when (epoca) {
        "Ibero-Romanas" -> R.drawable.ic_romana30
        "Medievales" -> R.drawable.ic_medieval30
        "Renacentistas" -> R.drawable.ic_renacentista30
        "Barrocas" -> R.drawable.ic_barroco30
        else -> R.drawable.ic_napoleonica30
    }
}