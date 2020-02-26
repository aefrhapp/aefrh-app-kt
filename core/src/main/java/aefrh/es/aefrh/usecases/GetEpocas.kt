package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.EpocaDataSource

class GetEpocas(private val epocaRepository: EpocaDataSource) {
    suspend operator fun invoke() = epocaRepository.getAll()
}