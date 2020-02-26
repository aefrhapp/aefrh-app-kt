package aefrh.es.aefrh.remote

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.utils.Result
import aefrh.es.aefrh.utils.awaitTaskResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import org.koin.core.KoinComponent

private const val COLLECTION_EPOCAS = "Epocas"

interface IFirebaseRepository {
    suspend fun getEpocas(): Result<Exception, List<Epoca>>
}

class FirebaseRepository(
    private val firebaseFirestore: FirebaseFirestore
) : IFirebaseRepository, KoinComponent {

    override suspend fun getEpocas(): Result<Exception, List<Epoca>> {
        return try {
            val task = awaitTaskResult(
                firebaseFirestore.collection(COLLECTION_EPOCAS).get()
            )
            resultToNoteList(task)
        } catch (exception: Exception) {
            Result.build { throw exception }
        }
    }

    private fun resultToNoteList(result: QuerySnapshot?): Result<Exception, List<Epoca>> {
        val noteList = mutableListOf<Epoca>()

        result?.forEach { documentSnapshot ->
            noteList.add(documentSnapshot.toObject(Epoca::class.java))
        }

        return Result.build {
            noteList
        }

    }

}