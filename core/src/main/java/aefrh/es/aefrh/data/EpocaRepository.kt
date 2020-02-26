package aefrh.es.aefrh.data

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.usecases.UseCaseResult

class EpocaRepository(private val epocaApi: EpocaApi): EpocaDataSource {

    override suspend fun getAll(): UseCaseResult<List<Epoca>> {
        return try {
            val result = epocaApi.getEpocas().await()
            UseCaseResult.Success(result.result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }

}