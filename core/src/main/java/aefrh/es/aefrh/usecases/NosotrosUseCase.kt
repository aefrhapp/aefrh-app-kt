package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.parse.ParseRepository

class NosotrosUseCase(private val parseRepository: ParseRepository) {
    suspend operator fun invoke() = parseRepository.getInterno()
}