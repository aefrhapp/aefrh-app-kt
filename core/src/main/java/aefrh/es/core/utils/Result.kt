package aefrh.es.core.utils

import aefrh.es.core.domain.Status
import aefrh.es.core.domain.Status.*

data class Result<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(SUCCESS, data, null)
        }

        fun <T> error(exception: Throwable): Result<T> {
            return Result(ERROR, null, exception.message)
        }

        fun <T> loading(): Result<T> {
            return Result(LOADING, null, null)
        }
    }
}