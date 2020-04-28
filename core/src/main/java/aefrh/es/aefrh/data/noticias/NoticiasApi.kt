package aefrh.es.aefrh.data.noticias

import aefrh.es.aefrh.domain.RssFeedAll
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NoticiasApi {
    @GET("rss")
    fun getNoticias(): Deferred<RssFeedAll>
}