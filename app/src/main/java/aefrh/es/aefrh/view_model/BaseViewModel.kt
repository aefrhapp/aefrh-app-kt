package aefrh.es.aefrh.view_model

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent {

    /**
     *  ApplicationRepository is injected here to access application level
     *  functions & preference
     *
     */
//    val appRepo: AppRepository by inject()

}