package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.parse.ParseRepository

class MapaUseCase(private val parseRepository: ParseRepository) {
    suspend operator fun invoke() = parseRepository.getAllFiestas()
//    suspend fun getFiestaById(fiestaId: String) = parseRepository.getFiestaById(fiestaId)
}