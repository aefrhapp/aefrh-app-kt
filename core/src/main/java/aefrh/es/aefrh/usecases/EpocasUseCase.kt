package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.parse.ParseRepository

class EpocasUseCase(private val parseRepository: ParseRepository) {
    suspend fun getAllEpocas() = parseRepository.getAll()
}