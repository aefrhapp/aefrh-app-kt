package aefrh.es.core.data.parse

import aefrh.es.core.domain.Epoca
import aefrh.es.core.domain.Fiesta
import aefrh.es.core.domain.Interno
import aefrh.es.core.utils.JSONResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ParseApi {
    @GET("Epocas?order=Orden&where={\"Activo\":true}")
    fun getEpocas(): Deferred<JSONResponse<Epoca>>

    @GET("Fiestas?order=Orden")
    fun getFiestaByEpocaId(@Query("where") jsonString: String, @Query("include") include: String = "Informacion"): Deferred<JSONResponse<Fiesta>>

    @GET("Fiestas/{id}")
    fun getFiestaById(@Path("id") fiestaId: String, @Query("include") include: String = "Informacion"): Deferred<Fiesta>

    @GET("Fiestas?where={\"Activo\":true}")
    fun getFiestas(): Deferred<JSONResponse<Fiesta>>

    @GET("Interno/RQSRHiM590")
    fun getInterno(): Deferred<Interno>
}