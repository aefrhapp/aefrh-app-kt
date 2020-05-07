package aefrh.es.core.data.noticias

import aefrh.es.core.domain.RssFeed
import aefrh.es.core.domain.RssFeedSingle
import aefrh.es.core.utils.Result
import timber.log.Timber

class NoticiasRepositoryImpl(private val noticiasApi: NoticiasApi): NoticiasRepository {
    override suspend fun getAllNoticias(): Result<RssFeed> {
        return try {
            val result = noticiasApi.getAllNoticias().await()
            Result.success(result)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.error(ex)
        }
    }

    override suspend fun getAllMagazine(): Result<RssFeed> {
        return try {
            val result = noticiasApi.getAllMagazine().await()
            Result.success(result)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.error(ex)
        }
    }

    override suspend fun getSingleNoticia(idNoticia: String): Result<RssFeedSingle> {
        return try {
            val result = noticiasApi.getSingleNoticia(idNoticia).await()
            Result.success(result)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.error(ex)
        }
    }

}