package aefrh.es.aefrh.data

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.usecases.UseCaseResult

interface EpocaDataSource {
    // Suspend is used to await the result from Deferred
    suspend fun getAll(): UseCaseResult<List<Epoca>>
}