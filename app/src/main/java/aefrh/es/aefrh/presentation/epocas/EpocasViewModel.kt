package aefrh.es.aefrh.presentation.epocas

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.EpocasUseCase
import aefrh.es.aefrh.utils.Result
import aefrh.es.aefrh.utils.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EpocasViewModel(private val epocaUseCase: EpocasUseCase): BaseViewModel() {

    private val _epocas = MutableLiveData<Result<List<Epoca>>>()
    val epocas : LiveData<Result<List<Epoca>>>
        get() = _epocas

    private val _epoca = SingleLiveEvent<String>()
    val epoca : LiveData<String>
        get() = _epoca

    init {
        viewModelScope.launch {
            _epocas.value = Result.loading()
            _epocas.value = epocaUseCase.getAllEpocas()
        }
    }

    fun onGoToFiestasByEppoca(epocaId: String) {
        _epoca.value = epocaId
    }

}