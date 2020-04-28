package aefrh.es.aefrh.data.parse

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.domain.Interno
import aefrh.es.aefrh.domain.ParseObject
import aefrh.es.aefrh.utils.Result
import timber.log.Timber

class ParseRepositoryImpl(private val parseApi: ParseApi): ParseRepository {

    override suspend fun getAll(): Result<List<Epoca>> {
        return try {
            val result = parseApi.getEpocas().await()
            Result.success(result.result)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.error(ex)
        }
    }

    override suspend fun getAllByEpocaId(epocaId: String): Result<List<Fiesta>> {
        return try {
            val jsonString = "{\"Epoca\":${ParseObject(className = "Epocas", id = epocaId).get()}, \"Activo\":true}"
            val result = parseApi.getFiestaByEpocaId(jsonString).await()
            Result.success(result.result)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.error(ex)
        }
    }

    override suspend fun getFiestaById(fiestaId: String): Result<Fiesta> {
        return try {
            val result = parseApi.getFiestaById(fiestaId).await()
            Result.success(result)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.error(ex)
        }
    }

    override suspend fun getAllFiestas(): Result<List<Fiesta>> {
        return try {
            val result = parseApi.getFiestas().await()
            Result.success(result.result)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.error(ex)
        }
    }

    override suspend fun getInterno(): Result<Interno> {
        return try {
            val result = parseApi.getInterno().await()
            Result.success(result)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.error(ex)
        }
    }

}