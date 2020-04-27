package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.parse.ParseRepository

class FiestasUseCase(private val parseRepository: ParseRepository) {
    suspend fun getAllFiestas() = parseRepository.getAllFiestas()
    suspend fun getFiestasByEpocaId(epocaId: String) = parseRepository.getAllByEpocaId(epocaId)
    suspend fun getFiestaById(fiestaId: String) = parseRepository.getFiestaById(fiestaId)
}