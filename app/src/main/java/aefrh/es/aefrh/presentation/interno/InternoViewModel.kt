package aefrh.es.aefrh.presentation.interno

import aefrh.es.aefrh.domain.Interno
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.InternoUseCase
import aefrh.es.aefrh.utils.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InternoViewModel(
    private val internoUseCase: InternoUseCase
): BaseViewModel() {

    private val _interno = MutableLiveData<Result<Interno>>()
    val interno : LiveData<Result<Interno>>
        get() = _interno

    private val _redesLink = MutableLiveData<String>()
    val redesLink : LiveData<String>
        get() = _redesLink

    private val _email = MutableLiveData<String>()
    val email : LiveData<String>
        get() = _email

    private val _videoCase = MutableLiveData<String>()
    val videoCase : LiveData<String>
        get() = _videoCase

    init {
        _interno.value = Result.loading()
        viewModelScope.launch {
            val result = internoUseCase.invoke()
            _interno.value = result
        }
    }

    fun onGoToRRSS(link: String) {
        _redesLink.value = link
    }

    fun onSendEmail(email: String) {
        _email.value = email
    }

    fun onGoToReproductor(videoId: String) {
        _videoCase.value = videoId
    }

}