package aefrh.es.aefrh.presentation.noticias

import aefrh.es.aefrh.domain.RssFeed
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.NoticiasUseCase
import aefrh.es.aefrh.utils.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoticiasViewModel(private val noticiasUseCase: NoticiasUseCase): BaseViewModel() {

    private val _noticias = MutableLiveData<Result<RssFeed>>()
    val noticias : LiveData<Result<RssFeed>>
        get() = _noticias

    fun onGetNoticias() {
        _noticias.value = Result.loading()
        viewModelScope.launch {
            _noticias.value = noticiasUseCase.getAllNoticias()
        }
    }

    fun onGetMagazine() {
        _noticias.value = Result.loading()
        viewModelScope.launch {
            _noticias.value = noticiasUseCase.getAllMagazine()
        }
    }

}