package aefrh.es.aefrh.data.epoca

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.utils.Result

interface EpocaDataSource {
    suspend fun getAll(): Result<List<Epoca>>
}