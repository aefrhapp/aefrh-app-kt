package aefrh.es.aefrh.presentation.epocas

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.domain.Result
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.GetEpocas
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EpocasViewModel(private val epocaUseCase: GetEpocas): BaseViewModel() {

    private val _epocas = MutableLiveData<Result<List<Epoca>>>()
    val epocas : LiveData<Result<List<Epoca>>>
        get() = _epocas

    init {
        viewModelScope.launch {
            _epocas.value = Result.loading()
            _epocas.value = epocaUseCase.invoke()
        }
    }

}