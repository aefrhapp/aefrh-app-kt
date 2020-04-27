package aefrh.es.aefrh.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    private val _errorStr = MutableLiveData<String>()
    val errorStr : LiveData<String>
        get() = _errorStr

    private val _errorInt = MutableLiveData<Int>()
    val errorInt : LiveData<Int>
        get() = _errorInt

    protected fun handleErrorStr(error: String?) {
        error.let { _errorStr.value = it }
    }

    fun handleErrorInt(error: Int?) {
        error.let { _errorInt.value = it }
    }

//    // Coroutine's background job
//    val job = Job()
//
//    // Define default thread for Coroutine as Main and add job
//    override val coroutineContext: CoroutineContext = job + Dispatchers.Main
//
//    override fun onCleared() {
//        super.onCleared()
//        // Clear our job when the linked activity is destroyed to avoid memory leaks
//        job.cancel()
//    }

}