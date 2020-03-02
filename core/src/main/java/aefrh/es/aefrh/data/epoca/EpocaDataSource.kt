package aefrh.es.aefrh.data.epoca

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.domain.Resource

interface EpocaDataSource {
    suspend fun getAll(): Resource<List<Epoca>>
}