package aefrh.es.aefrh.presentation.main

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.GetEpocas
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val epocaUseCase: GetEpocas
): BaseViewModel() {

    private val showLoading = MutableLiveData<Boolean>()
    val epocaList = MutableLiveData<List<Epoca>>()
    val showError = MutableLiveData<String>()

    init {
        // Load cats when this ViewModel is instantiated.
        loadEpocas()
    }

    private fun loadEpocas() {

        // Show progressBar during the operation on the MAIN (default) thread
        showLoading.value = true

        // launch the Coroutine
        launch {

            // Switching from MAIN to IO thread for API operation
            // Update our data list with the new one from API
            val result = withContext(Dispatchers.IO) {
                epocaUseCase.invoke()
            }

            // Hide progressBar once the operation is done on the MAIN (default) thread
            showLoading.value = false

            when (result.status) {
                Status.SUCCESS -> epocaList.value = result.data
                else -> showError.value = result.message
            }

        }

    }

}