package aefrh.es.aefrh.presentation.nosotros

import aefrh.es.aefrh.domain.Interno
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.NosotrosUseCase
import aefrh.es.aefrh.utils.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NosotrosViewModel(
    private val nosotrosUseCase: NosotrosUseCase
): BaseViewModel() {

    private val _interno = MutableLiveData<Result<Interno>>()
    val interno : LiveData<Result<Interno>>
        get() = _interno

    init {
        _interno.value = Result.loading()
        viewModelScope.launch {
            val result = nosotrosUseCase.invoke()
            _interno.value = result
        }
    }

}