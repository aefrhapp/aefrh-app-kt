package aefrh.es.aefrh.data.fiesta

import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.domain.ParseObject
import aefrh.es.aefrh.domain.Result

class FiestaRepository(private val fiestaApi: FiestaApi):
    FiestaDataSource {

    override suspend fun getAllByEpocaId(epocaId: String): Result<List<Fiesta>> {
        return try {
            val jsonString = "{\"Epoca\":${ParseObject(className = "Epocas", id = epocaId).get()}}"
            val result = fiestaApi.getFiestaByEpocaId(jsonString).await()
            Result.success(result.result)
        } catch (ex: Exception) {
            Result.error(ex)
        }
    }

    override suspend fun getFiestaById(fiestaId: String): Result<Fiesta> {
        return try {
            val result = fiestaApi.getFiestaById(fiestaId).await()
            Result.success(result)
        } catch (ex: Exception) {
            Result.error(ex)
        }
    }

}