package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.parse.ParseRepository

class InternoUseCase(private val parseRepository: ParseRepository) {
    suspend operator fun invoke() = parseRepository.getInterno()
}