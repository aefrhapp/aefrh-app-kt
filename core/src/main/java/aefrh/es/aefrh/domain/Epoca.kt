package aefrh.es.aefrh.domain

import com.google.gson.annotations.SerializedName

data class Epoca(
    @SerializedName("objectId")
    val id: String,
    @SerializedName("Nombre")
    val nombre: String,
    @SerializedName("Orden")
    val orden: Int,
    @SerializedName("Activo")
    val activo: Boolean,
    @SerializedName("Icono")
    val icono: Imagen,
    @SerializedName("Imagen")
    val imagen: Imagen
)