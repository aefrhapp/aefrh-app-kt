package aefrh.es.aefrh.domain

import com.google.gson.annotations.SerializedName

data class Imagen(
    @SerializedName("__type")
    val type: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)