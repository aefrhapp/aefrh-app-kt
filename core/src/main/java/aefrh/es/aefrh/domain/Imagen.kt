package aefrh.es.aefrh.domain

import com.google.gson.annotations.SerializedName

data class Imagen(
    @SerializedName("__type")
    val Type: String,
    @SerializedName("name")
    val Name: String,
    @SerializedName("url")
    val Url: String
)