package aefrh.es.aefrh.presentation.noticias

import aefrh.es.core.domain.RssFeed
import aefrh.es.core.domain.RssFeedSingle
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.core.usecases.NoticiasUseCase
import aefrh.es.core.utils.Result
import aefrh.es.core.utils.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoticiasViewModel(private val noticiasUseCase: NoticiasUseCase): BaseViewModel() {

    private val _noticias = MutableLiveData<Result<RssFeed>>()
    val noticias : LiveData<Result<RssFeed>>
        get() = _noticias

    private val _noticia = MutableLiveData<Result<RssFeedSingle>>()
    val noticia : LiveData<Result<RssFeedSingle>>
        get() = _noticia

    private val _noticiaId = SingleLiveEvent<String>()
    val noticiaId : LiveData<String>
        get() = _noticiaId

    private var firstTime = true

    fun onGetNoticias() {
        if(firstTime) {
            _noticias.value = Result.loading()
            viewModelScope.launch {
                _noticias.value = noticiasUseCase.getAllNoticias()
            }
            firstTime = false
        }
    }

    fun onGetMagazine() {
        if(firstTime) {
            _noticias.value = Result.loading()
            viewModelScope.launch {
                _noticias.value = noticiasUseCase.getAllMagazine()
            }
            firstTime = false
        }
    }

    fun onGetSingleNoticia(idNoticia: String) {
        _noticia.value = Result.loading()
        viewModelScope.launch {
            _noticia.value = noticiasUseCase.getSingleNoticia(idNoticia)
        }
    }

    fun onGoToNoticiaDetail(noticiaId: String) {
        _noticiaId.value = noticiaId
    }

}