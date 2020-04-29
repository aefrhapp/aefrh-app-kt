package aefrh.es.aefrh.data.noticias

import aefrh.es.aefrh.domain.RssFeed
import aefrh.es.aefrh.domain.RssFeedSingle
import aefrh.es.aefrh.utils.Result

interface NoticiasRepository {
    suspend fun getAllNoticias(): Result<RssFeed>
    suspend fun getAllMagazine(): Result<RssFeed>
    suspend fun getSingleNoticia(idNoticia: String): Result<RssFeedSingle>
}