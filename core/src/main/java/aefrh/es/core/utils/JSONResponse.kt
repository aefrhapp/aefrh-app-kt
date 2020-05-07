package aefrh.es.core.utils

import com.google.gson.annotations.SerializedName

data class JSONResponse<T>(
    @SerializedName("results")
    val result: List<T>
)