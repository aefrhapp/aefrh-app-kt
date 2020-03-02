package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.fiesta.FiestaDataSource

class GetFiestas(private val fiestaRepository: FiestaDataSource) {
    suspend operator fun invoke(epocaId: String) = fiestaRepository.getAllByEpocaId(epocaId)
}