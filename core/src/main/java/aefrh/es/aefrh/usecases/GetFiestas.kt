package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.parse.ParseRepository

class GetFiestas(private val parseRepository: ParseRepository) {
    suspend operator fun invoke(epocaId: String) = parseRepository.getAllByEpocaId(epocaId)
    suspend fun getFiestaById(fiestaId: String) = parseRepository.getFiestaById(fiestaId)
}