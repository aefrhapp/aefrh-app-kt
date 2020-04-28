package aefrh.es.aefrh.data.noticias

import aefrh.es.aefrh.domain.RssFeedAll
import aefrh.es.aefrh.utils.Result

interface NoticiasRepository {
    suspend fun getAllNoticias(): Result<RssFeedAll>
}