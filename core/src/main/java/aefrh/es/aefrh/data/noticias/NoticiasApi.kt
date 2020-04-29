package aefrh.es.aefrh.data.noticias

import aefrh.es.aefrh.domain.RssFeed
import aefrh.es.aefrh.domain.RssFeedSingle
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticiasApi {
    @GET("rss")
    fun getAllNoticias(): Deferred<RssFeed>
    @GET("category/taxi_magazine_publica/rss")
    fun getAllMagazine(): Deferred<RssFeed>
    @GET("?feed=rdf")
    fun getSingleNoticia(@Query("p")idNoticia: String): Deferred<RssFeedSingle>
}