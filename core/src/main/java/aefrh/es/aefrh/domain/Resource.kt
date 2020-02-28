package aefrh.es.aefrh.domain

import aefrh.es.aefrh.domain.Status.*

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(exception: Throwable): Resource<T> {
            return Resource(ERROR, null, exception.message)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}