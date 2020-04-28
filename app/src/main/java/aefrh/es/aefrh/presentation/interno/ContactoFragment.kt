package aefrh.es.aefrh.presentation.interno

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentContactoBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.utils.goToBrowser
import aefrh.es.aefrh.utils.sendEmail
import android.view.View
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class ContactoFragment: BaseFragment<FragmentContactoBinding, InternoViewModel>() {

    override val viewModel: InternoViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_contacto

    override fun init(view: View) {

        // Bind viewmodel
        bindingObject.viewModel = viewModel
        viewModel.getInterno()

        // Oberve
        viewModel.interno.observe(this, Observer { result ->

            when(result.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    displayErrorInt(R.string.error2)
                    Timber.e(result.message)
                }
                else -> {
                    hideProgress()
                    val interno = result.data
                    bindingObject.interno = interno
                }
            }

        })

        viewModel.redesLink.observe(this, Observer {
            context?.goToBrowser(it)
        })

        viewModel.email.observe(this, Observer {
            context?.sendEmail(it)
        })

    }

}