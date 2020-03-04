package aefrh.es.aefrh.data.fiesta

import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.domain.Resource

class FiestaRepository(private val fiestaApi: FiestaApi):
    FiestaDataSource {

    override suspend fun getAllByEpocaId(epocaId: String): Resource<List<Fiesta>> {
        return try {
            val jsonString = "{\"Epoca\":{\"__type\":\"Pointer\",\"className\":\"Epocas\",\"objectId\":\"$epocaId\"}}"
            val result = fiestaApi.getFiestaByEpocaId(jsonString).await()
            Resource.success(result.result)
        } catch (ex: Exception) {
            Resource.error(ex)
        }
    }

    override suspend fun getFiestaById(fiestaId: String): Resource<Fiesta> {
        return try {
            val result = fiestaApi.getFiestaById(fiestaId).await()
            Resource.success(result)
        } catch (ex: Exception) {
            Resource.error(ex)
        }
    }

}