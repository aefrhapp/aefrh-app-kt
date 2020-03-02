package aefrh.es.aefrh.domain

import com.google.gson.annotations.SerializedName

data class Informacion(
    @SerializedName("objectId")
    val id: String,
    @SerializedName("Localidad")
    val localidad: String
)