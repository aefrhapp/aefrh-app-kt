package aefrh.es.aefrh.data.epoca

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.domain.Resource

class EpocaRepository(private val epocaApi: EpocaApi):
    EpocaDataSource {

    override suspend fun getAll(): Resource<List<Epoca>> {
        return try {
            val result = epocaApi.getEpocas().await()
            Resource.success(result.result)
        } catch (ex: Exception) {
            Resource.error(ex)
        }
    }

}