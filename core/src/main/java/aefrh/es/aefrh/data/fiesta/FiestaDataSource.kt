package aefrh.es.aefrh.data.fiesta

import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.domain.Resource

interface FiestaDataSource {
    suspend fun getAllByEpocaId(epocaId: String): Resource<List<Fiesta>>
    suspend fun getFiestaById(fiestaId: String): Resource<Fiesta>
}