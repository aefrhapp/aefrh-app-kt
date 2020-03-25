package aefrh.es.aefrh.presentation.epocas

import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.domain.Resource
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.GetEpocas
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpocasViewModel(private val epocaUseCase: GetEpocas): BaseViewModel() {

    val epocas = MutableLiveData<Resource<List<Epoca>>>().apply {
        Resource.loading(emptyList<Epoca>())
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            epocas.postValue(epocaUseCase.invoke())
        }
    }

}