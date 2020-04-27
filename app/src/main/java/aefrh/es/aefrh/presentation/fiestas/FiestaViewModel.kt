package aefrh.es.aefrh.presentation.fiestas

import aefrh.es.aefrh.Bootstrap
import aefrh.es.aefrh.R
import aefrh.es.aefrh.domain.*
import aefrh.es.aefrh.presentation.base.BaseViewModel
import aefrh.es.aefrh.usecases.FiestasUseCase
import aefrh.es.aefrh.utils.Result
import aefrh.es.aefrh.utils.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FiestaViewModel(
    private val fiestaUseCase: FiestasUseCase
): BaseViewModel() {

    private val _fiestas = MutableLiveData<Result<List<Fiesta>>>()
    val fiestas : LiveData<Result<List<Fiesta>>>
        get() = _fiestas

    private val _fiesta = MutableLiveData<Result<Fiesta>>()
    val fiesta : LiveData<Result<Fiesta>>
        get() = _fiesta

    private val _redesLink = MutableLiveData<String>()
    val redesLink : LiveData<String>
        get() = _redesLink

    private val _informacionList = MutableLiveData<List<InformacionItem>>()
    val informacionList : LiveData<List<InformacionItem>>
        get() = _informacionList

    private val _contactoList = MutableLiveData<List<ContactoItem>>()
    val contactoList : LiveData<List<ContactoItem>>
        get() = _contactoList

    private val _contacto = MutableLiveData<ContactoItem>()
    val contacto : LiveData<ContactoItem>
        get() = _contacto

    private val _fiestaStr = SingleLiveEvent<String>()
    val fiestaStr : LiveData<String>
        get() = _fiestaStr

    fun getFiestas(epocaId: String) {
        _fiestas.value = Result.loading()
        viewModelScope.launch {
            _fiestas.value = epocaId.let { fiestaUseCase.getFiestasByEpocaId(it) }
        }
    }

    fun getFiestaById(fiestaId: String) {
        _fiesta.value = Result.loading()
        viewModelScope.launch {
            val result = fiestaId.let { fiestaUseCase.getFiestaById(it) }
            _fiesta.value = result
            extractInformacionItem(result.data?.informacion)
            extractContactoItem(result.data?.informacion)
        }
    }

    private fun extractInformacionItem(informacion: Informacion?) {

        if(informacion != null) {

            val mutableList = mutableListOf<InformacionItem>()
            informacion.denominacion.let {
                mutableList.add(InformacionItem(Bootstrap.instance.getString(R.string.denominacion), it))
            }
            informacion.year.let {
                mutableList.add(InformacionItem(Bootstrap.instance.getString(R.string.ano), it))
            }
            informacion.epoca.let {
                mutableList.add(InformacionItem(Bootstrap.instance.getString(R.string.epoca), it))
            }
            informacion.localidad.let {
                mutableList.add(InformacionItem(Bootstrap.instance.getString(R.string.localidad), it))
            }
            informacion.organizacion.let {
                mutableList.add(InformacionItem(Bootstrap.instance.getString(R.string.organizacion), it))
            }
            informacion.fecha.let {
                mutableList.add(InformacionItem(Bootstrap.instance.getString(R.string.fecha), it))
            }

            // Set data
            _informacionList.value = mutableList

        }

    }

    private fun extractContactoItem(informacion: Informacion?) {

        if(informacion != null) {

            val mutableList = mutableListOf<ContactoItem>()
            if(!informacion.web.isNullOrEmpty()) {
                mutableList.add(ContactoItem(R.drawable.ic_web, Bootstrap.instance.getString(R.string.web), informacion.web, ContactoCase.WEB))
            }
            if(!informacion.email.isNullOrEmpty()) {
                mutableList.add(ContactoItem(R.drawable.ic_email, Bootstrap.instance.getString(R.string.email), informacion.email, ContactoCase.EMAIL))
            }
            if(!informacion.telefono.isNullOrEmpty()) {
                mutableList.add(ContactoItem(R.drawable.ic_phone, Bootstrap.instance.getString(R.string.telefono), informacion.telefono, ContactoCase.PHONE))
            }

            // Set data
            _contactoList.value = mutableList

        }

    }

    fun onGoToRRSS(link: String) {
        _redesLink.value = link
    }

    fun onGoToContact(contactoItem: ContactoItem) {
        _contacto.value = contactoItem
    }

    fun onGoToFiestaDetail(fiestaId: String) {
        _fiestaStr.value = fiestaId
    }

}