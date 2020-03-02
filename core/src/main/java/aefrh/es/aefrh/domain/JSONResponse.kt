package aefrh.es.aefrh.domain

import com.google.gson.annotations.SerializedName

data class JSONResponse<T>(
    @SerializedName("results")
    val result: List<T>
)