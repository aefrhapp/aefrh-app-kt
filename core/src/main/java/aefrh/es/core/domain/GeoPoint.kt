package aefrh.es.core.domain

import com.google.gson.annotations.SerializedName

data class GeoPoint(
    @SerializedName("__type")
    val type: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)