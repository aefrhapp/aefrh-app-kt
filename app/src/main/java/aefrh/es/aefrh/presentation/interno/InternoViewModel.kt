package aefrh.es.aefrh.presentation.interno

import aefrh.es.core.domain.Interno
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.core.usecases.InternoUseCase
import aefrh.es.core.utils.Result
import aefrh.es.core.utils.SingleLiveEvent
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

    private val _videoId = SingleLiveEvent<String>()
    val videoId : LiveData<String>
        get() = _videoId

    fun getInterno() {
        _interno.value = Result.loading()
        viewModelScope.launch {
            val result = internoUseCase.getInterno()
            _interno.value = result
        }
    }

    fun onGoToRRSS(link: String) {
        _redesLink.value = link
    }

    fun onSendEmail(email: String) {
        _email.value = email
    }

    fun onGoToPlayer(videoId: String) {
        _videoId.value = videoId
    }

}