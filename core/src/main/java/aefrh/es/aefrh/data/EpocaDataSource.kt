package aefrh.es.aefrh.data

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.domain.Resource

interface EpocaDataSource {
    // Suspend is used to await the result from Deferred
    suspend fun getAll(): Resource<List<Epoca>>
}