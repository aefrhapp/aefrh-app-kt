package aefrh.es.aefrh.data.noticias

import aefrh.es.aefrh.domain.RssFeed
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NoticiasApi {
    @GET("rss")
    fun getAllNoticias(): Deferred<RssFeed>
    @GET("category/taxi_magazine_publica/rss")
    fun getAllMagazine(): Deferred<RssFeed>
}