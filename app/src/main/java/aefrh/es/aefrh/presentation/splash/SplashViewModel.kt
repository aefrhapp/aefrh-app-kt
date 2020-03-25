package aefrh.es.aefrh.presentation.splash

import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.utils.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {

    private val _isCurrentUser = SingleLiveEvent<Boolean>()
    val isCurrentUser : LiveData<Boolean>
        get() = _isCurrentUser

    init {
        viewModelScope.launch {
            delay(1500)
            _isCurrentUser.value = true
        }
    }

}