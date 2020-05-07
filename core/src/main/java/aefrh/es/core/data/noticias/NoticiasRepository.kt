package aefrh.es.core.data.noticias

import aefrh.es.core.domain.RssFeed
import aefrh.es.core.domain.RssFeedSingle
import aefrh.es.core.utils.Result

interface NoticiasRepository {
    suspend fun getAllNoticias(): Result<RssFeed>
    suspend fun getAllMagazine(): Result<RssFeed>
    suspend fun getSingleNoticia(idNoticia: String): Result<RssFeedSingle>
}