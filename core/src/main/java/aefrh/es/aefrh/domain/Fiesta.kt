package aefrh.es.aefrh.domain

import com.google.gson.annotations.SerializedName

data class Fiesta(
    @SerializedName("objectId")
    val Id: String,
    @SerializedName("Nombre")
    val Nombre: String,
    @SerializedName("Imagen_principal")
    val Imagen_principal: Imagen
)