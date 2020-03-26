package aefrh.es.aefrh.data.fiesta

import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.utils.Result

interface FiestaDataSource {
    suspend fun getAllByEpocaId(epocaId: String): Result<List<Fiesta>>
    suspend fun getFiestaById(fiestaId: String): Result<Fiesta>
}