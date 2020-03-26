package aefrh.es.aefrh.data.epoca

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.domain.Result

class EpocaRepository(private val epocaApi: EpocaApi):
    EpocaDataSource {

    override suspend fun getAll(): Result<List<Epoca>> {
        return try {
            val result = epocaApi.getEpocas().await()
            Result.success(result.result)
        } catch (ex: Exception) {
            Result.error(ex)
        }
    }

}