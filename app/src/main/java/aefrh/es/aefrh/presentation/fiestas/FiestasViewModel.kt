package aefrh.es.aefrh.presentation.fiestas

import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.domain.Resource
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.GetFiestas
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FiestasViewModel(
    private val fiestaUseCase: GetFiestas
): BaseViewModel() {

    val fiestas = MutableLiveData<Resource<List<Fiesta>>>().apply {
        Resource.loading(emptyList<Fiesta>())
    }

    val fiesta = MutableLiveData<Resource<Fiesta>>()

    fun getFiestas(epocaId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            fiestas.postValue(epocaId?.let { fiestaUseCase.invoke(it) })
        }
    }

    fun getFiestaById(fiestaId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            fiesta.postValue(fiestaId?.let { fiestaUseCase.getFiestaById(it) })
        }
    }

}