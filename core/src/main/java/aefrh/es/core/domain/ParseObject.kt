package aefrh.es.core.domain

import com.google.gson.annotations.SerializedName

data class ParseObject(
    @SerializedName("__type")
    val type: String = "Pointer",
    @SerializedName("className")
    val className: String,
    @SerializedName("objectId")
    val id: String
) {
    fun get(): String {
        return "{\"__type\":\"$type\",\"className\":\"$className\",\"objectId\":\"$id\"}"
    }
}