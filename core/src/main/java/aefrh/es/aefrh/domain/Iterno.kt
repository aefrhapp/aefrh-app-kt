package aefrh.es.aefrh.domain

import com.google.gson.annotations.SerializedName

data class Interno(
    @SerializedName("objectId")
    val id: String,
    @SerializedName("Imagen_mapa_spain")
    val imagen_mapa_spain: Imagen,
    @SerializedName("Imagen_mapa_spain")
    val imagen_mapa_europe: Imagen,
    @SerializedName("Imagen_audio_visual")
    val imagen_audio_visual: Imagen,
    @SerializedName("Imagen_video")
    val imagen_video: Imagen,
    @SerializedName("AudioVisual")
    val audioVisual: String,
    @SerializedName("Video")
    val video: String,
    @SerializedName("Revista")
    val revista: String,
    @SerializedName("Presidencia")
    val presidencia: String,
    @SerializedName("RRSS")
    val rrss: String,
    @SerializedName("GoogleLink")
    val googleLink: String
)