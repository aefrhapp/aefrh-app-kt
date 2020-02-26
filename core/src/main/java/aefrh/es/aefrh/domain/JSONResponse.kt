package aefrh.es.aefrh.domain

import com.google.gson.annotations.SerializedName

data class JSONResponse(
    @SerializedName("results")
    val result: List<Epoca>
)