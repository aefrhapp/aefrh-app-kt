package aefrh.es.core.domain

import com.google.gson.annotations.SerializedName

data class Informacion(
    @SerializedName("objectId")
    val id: String,
    @SerializedName("Localidad")
    val localidad: String,
    @SerializedName("Epoca")
    val epoca: String,
    @SerializedName("Fecha")
    val fecha: String,
    @SerializedName("Ano")
    val year: String,
    @SerializedName("Organizacion")
    val organizacion: String,
    @SerializedName("Denominacion")
    val denominacion: String,

    @SerializedName("Telefono")
    val telefono: String,
    @SerializedName("Email")
    val email: String,
    @SerializedName("Web")
    val web: String,

    @SerializedName("Youtube")
    val youtube: String,
    @SerializedName("Facebook")
    val facebook: String,
    @SerializedName("Instagram")
    val instagram: String,
    @SerializedName("Twitter")
    val twitter: String
)