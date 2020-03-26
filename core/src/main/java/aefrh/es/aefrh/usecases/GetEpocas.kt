package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.parse.ParseRepository

class GetEpocas(private val parseRepository: ParseRepository) {
    suspend operator fun invoke() = parseRepository.getAll()
}