package aefrh.es.aefrh.domain

import com.google.gson.annotations.SerializedName

data class Fiesta(
    @SerializedName("objectId")
    val id: String,
    @SerializedName("Nombre")
    val nombre: String,
    @SerializedName("Titulo")
    val titulo: String,
    @SerializedName("Contenido")
    val contenido: String,
    @SerializedName("Imagen_principal")
    val imagen_principal: Imagen,
    @SerializedName("Imagenes")
    val imagenes: List<Imagen>,
    @SerializedName("Informacion")
    val informacion: Informacion,
    @SerializedName("Localizacion")
    val localizacion: GeoPoint,
    @SerializedName("Tipo")
    val tipo: String
)