package aefrh.es.core.data.parse

import aefrh.es.core.domain.Epoca
import aefrh.es.core.domain.Fiesta
import aefrh.es.core.domain.Interno
import aefrh.es.core.utils.Result

interface ParseRepository {
    suspend fun getAll(): Result<List<Epoca>>
    suspend fun getAllByEpocaId(epocaId: String): Result<List<Fiesta>>
    suspend fun getFiestaById(fiestaId: String): Result<Fiesta>
    suspend fun getAllFiestas(): Result<List<Fiesta>>
    suspend fun getInterno(): Result<Interno>
}