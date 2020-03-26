package aefrh.es.aefrh.presentation.fiestas

import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.utils.Result
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.GetFiestas
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FiestasViewModel(
    private val fiestaUseCase: GetFiestas
): BaseViewModel() {

    private val _fiestas = MutableLiveData<Result<List<Fiesta>>>()
    val fiestas : LiveData<Result<List<Fiesta>>>
        get() = _fiestas

    private val _fiesta = MutableLiveData<Result<Fiesta>>()
    val fiesta : LiveData<Result<Fiesta>>
        get() = _fiesta

    fun getFiestas(epocaId: String?) {
        _fiestas.value = Result.loading()
        viewModelScope.launch {
            _fiestas.value = epocaId?.let { fiestaUseCase.invoke(it) }
        }
    }

    fun getFiestaById(fiestaId: String?) {
        _fiesta.value = Result.loading()
        viewModelScope.launch {
            _fiesta.value = fiestaId?.let { fiestaUseCase.getFiestaById(it) }
        }
    }

}