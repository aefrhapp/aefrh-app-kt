package aefrh.es.aefrh.view.main

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.usecases.GetEpocas
import aefrh.es.aefrh.usecases.UseCaseResult
import aefrh.es.aefrh.view.base.BaseViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val epocaUseCase: GetEpocas
): BaseViewModel() {

    val showLoading = MutableLiveData<Boolean>()
    val epocaList = MutableLiveData<List<Epoca>>()
    val showError = MutableLiveData<String>()

    init {
        // Load cats when this ViewModel is instantiated.
        loadCats()
    }

    private fun loadCats() {

        // Show progressBar during the operation on the MAIN (default) thread
        showLoading.value = true

        // launch the Coroutine
        launch {

            // Switching from MAIN to IO thread for API operation
            // Update our data list with the new one from API
            val result = withContext(Dispatchers.IO) { epocaUseCase.invoke() }

            // Hide progressBar once the operation is done on the MAIN (default) thread
            showLoading.value = false
            when (result) {
                is UseCaseResult.Success -> epocaList.value = result.data
                is UseCaseResult.Error -> showError.value = result.exception.message
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        // Clear our job when the linked activity is destroyed to avoid memory leaks
        job.cancel()
    }

}