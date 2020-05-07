package aefrh.es.core.usecases

import aefrh.es.core.data.parse.ParseRepository

class FiestasUseCase(private val parseRepository: ParseRepository) {
    suspend fun getAllFiestas() = parseRepository.getAllFiestas()
    suspend fun getFiestasByEpocaId(epocaId: String) = parseRepository.getAllByEpocaId(epocaId)
    suspend fun getFiestaById(fiestaId: String) = parseRepository.getFiestaById(fiestaId)
}