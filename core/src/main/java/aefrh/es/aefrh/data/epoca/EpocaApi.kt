package aefrh.es.aefrh.data.epoca

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.utils.JSONResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface EpocaApi {
    @GET("Epocas?order=Orden&where={\"Activo\":true}")
    fun getEpocas(): Deferred<JSONResponse<Epoca>>
}