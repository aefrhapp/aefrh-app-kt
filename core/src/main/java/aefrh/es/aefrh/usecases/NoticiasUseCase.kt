package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.noticias.NoticiasRepository

class NoticiasUseCase(private val noticiasRepository: NoticiasRepository) {
    suspend operator fun invoke() = noticiasRepository.getAllNoticias()
}