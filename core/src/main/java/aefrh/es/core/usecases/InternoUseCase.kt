package aefrh.es.core.usecases

import aefrh.es.core.data.parse.ParseRepository

class InternoUseCase(private val parseRepository: ParseRepository) {
    suspend fun getInterno() = parseRepository.getInterno()
}