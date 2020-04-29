package aefrh.es.aefrh.presentation.mapa

import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.FiestasUseCase
import aefrh.es.aefrh.utils.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MapaViewModel(
    private val fiestaUseCase: FiestasUseCase
): BaseViewModel() {

    private val _fiestas = MutableLiveData<Result<List<Fiesta>>>()
    val fiestas : LiveData<Result<List<Fiesta>>>
        get() = _fiestas

    private val _fiesta = MutableLiveData<Result<Fiesta>>()
    val fiesta : LiveData<Result<Fiesta>>
        get() = _fiesta

    private var firstTime = true

    fun onGetAllFiestas() {
        if(firstTime) {
            _fiestas.value = Result.loading()
            viewModelScope.launch {
                _fiestas.value = fiestaUseCase.getAllFiestas()
            }
            firstTime = false
        }
    }

    fun onGetSingleFiesta(fiestaId: String) {
        if(firstTime) {
            _fiesta.value = Result.loading()
            viewModelScope.launch {
                _fiesta.value = fiestaUseCase.getFiestaById(fiestaId)
            }
            firstTime = false
        }
    }

}