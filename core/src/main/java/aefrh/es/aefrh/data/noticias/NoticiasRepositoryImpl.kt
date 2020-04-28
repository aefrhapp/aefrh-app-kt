package aefrh.es.aefrh.data.noticias

import aefrh.es.aefrh.domain.RssFeedAll
import aefrh.es.aefrh.utils.Result
import timber.log.Timber

class NoticiasRepositoryImpl(private val noticiasApi: NoticiasApi): NoticiasRepository {
    override suspend fun getAllNoticias(): Result<RssFeedAll> {
        return try {
            val result = noticiasApi.getNoticias().await()
            Result.success(result)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.error(ex)
        }
    }
}