package aefrh.es.aefrh.presentation.noticias

import aefrh.es.aefrh.domain.RssFeedAll
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.NoticiasUseCase
import aefrh.es.aefrh.utils.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoticiasViewModel(private val noticiasUseCase: NoticiasUseCase): BaseViewModel() {

    private val _noticias = MutableLiveData<Result<RssFeedAll>>()
    val noticias : LiveData<Result<RssFeedAll>>
        get() = _noticias

    init {
        _noticias.value = Result.loading()
        viewModelScope.launch {
            _noticias.value = noticiasUseCase.invoke()
        }
    }

}