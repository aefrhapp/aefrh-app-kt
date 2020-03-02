package aefrh.es.aefrh.data.epoca

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.domain.JSONResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface EpocaApi {
    @GET("Epocas")
    fun getEpocas(): Deferred<JSONResponse<Epoca>>
}