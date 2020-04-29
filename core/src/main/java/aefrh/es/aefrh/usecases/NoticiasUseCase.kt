package aefrh.es.aefrh.usecases

import aefrh.es.aefrh.data.noticias.NoticiasRepository

class NoticiasUseCase(private val noticiasRepository: NoticiasRepository) {
    suspend fun getAllNoticias() = noticiasRepository.getAllNoticias()
    suspend fun getAllMagazine() = noticiasRepository.getAllMagazine()
    suspend fun getSingleNoticia(idNoticia: String) = noticiasRepository.getSingleNoticia(idNoticia)
}