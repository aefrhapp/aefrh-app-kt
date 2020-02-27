package aefrh.es.aefrh.domain

import com.google.gson.annotations.SerializedName

data class Epoca(
    @SerializedName("objectId")
    val Id: String,
    @SerializedName("Nombre")
    val Nombre: String,
    @SerializedName("Orden")
    val Orden: Int,
    @SerializedName("Activo")
    val Activo: Boolean,
    @SerializedName("Icono")
    val Icono: Imagen,
    @SerializedName("Imagen")
    val Imagen: Imagen
)