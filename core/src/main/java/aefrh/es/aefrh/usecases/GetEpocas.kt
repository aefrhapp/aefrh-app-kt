package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.epoca.EpocaDataSource

class GetEpocas(private val epocaRepository: EpocaDataSource) {
    suspend operator fun invoke() = epocaRepository.getAll()
}