package aefrh.es.aefrh.data.parse

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.utils.Result

interface ParseRepository {
    suspend fun getAll(): Result<List<Epoca>>
    suspend fun getAllByEpocaId(epocaId: String): Result<List<Fiesta>>
    suspend fun getFiestaById(fiestaId: String): Result<Fiesta>
    suspend fun getAllFiestas(): Result<List<Fiesta>>
}