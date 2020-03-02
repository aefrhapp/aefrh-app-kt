package aefrh.es.aefrh.data.fiesta

import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.domain.Resource

interface FiestaDataSource {
    suspend fun getAllByEpocaId(epocaId: String): Resource<List<Fiesta>>
}