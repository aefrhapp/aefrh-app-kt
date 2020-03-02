package aefrh.es.aefrh.data.fiesta

import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.domain.JSONResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface FiestaApi {
    @GET("Fiestas")
    fun getFiestaByEpocaId(@Query("where") jsonString: String): Deferred<JSONResponse<Fiesta>>
}