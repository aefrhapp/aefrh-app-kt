package aefrh.es.aefrh.presentation.mapa

import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.MapaUseCase
import aefrh.es.aefrh.utils.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MapaViewModel(
    private val mapaUseCase: MapaUseCase
): BaseViewModel() {

    private val _fiestas = MutableLiveData<Result<List<Fiesta>>>()
    val fiestas : LiveData<Result<List<Fiesta>>>
        get() = _fiestas

//    fun getFiestas(epocaId: String?) {
//        _fiestas.value = Result.loading()
//        viewModelScope.launch {
//            _fiestas.value = epocaId?.let { mapaUseCase.invoke(it) }
//        }
//    }

}