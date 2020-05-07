package aefrh.es.core.domain

import com.google.gson.annotations.SerializedName

data class Interno(
    @SerializedName("objectId")
    val id: String,
    @SerializedName("Nombre")
    val nombre: String,
    @SerializedName("Contenido")
    val contenido: String,
    @SerializedName("RRSS")
    val rrss: String,
    @SerializedName("Presidencia")
    val presidencia: String,

    @SerializedName("Youtube")
    val youtube: String,
    @SerializedName("Facebook")
    val facebook: String,
    @SerializedName("Instagram")
    val instagram: String,
    @SerializedName("Twitter")
    val twitter: String,
    @SerializedName("Web")
    val web: String,
    @SerializedName("GoogleLink")
    val googleLink: String,

    @SerializedName("AudioVisual")
    val audioVisual: String,
    @SerializedName("Video")
    val video: String,
    @SerializedName("Revista")
    val revista: String,

    @SerializedName("Imagen_principal")
    val imagen_principal: Imagen,
    @SerializedName("Imagen_mapa_spain")
    val imagen_mapa_spain: Imagen,
    @SerializedName("Imagen_mapa_europe")
    val imagen_mapa_europe: Imagen,
    @SerializedName("Imagen_audio_visual")
    val imagen_audio_visual: Imagen,
    @SerializedName("Imagen_video")
    val imagen_video: Imagen
)