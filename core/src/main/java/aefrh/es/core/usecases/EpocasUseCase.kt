package aefrh.es.core.usecases

import aefrh.es.core.data.parse.ParseRepository

class EpocasUseCase(private val parseRepository: ParseRepository) {
    suspend fun getAllEpocas() = parseRepository.getAll()
}