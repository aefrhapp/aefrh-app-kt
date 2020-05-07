package aefrh.es.aefrh.presentation.fiestas.information

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentFiestaInformationBinding
import aefrh.es.core.domain.ContactoCase
import aefrh.es.core.domain.ContactoItem
import aefrh.es.core.domain.Fiesta
import aefrh.es.core.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.presentation.fiestas.FiestaViewModel
import aefrh.es.core.utils.Result
import aefrh.es.core.utils.goToBrowser
import aefrh.es.core.utils.makePhoneCall
import aefrh.es.core.utils.sendEmail
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_fiesta_information.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class FiestaInformationFragment : BaseFragment<FragmentFiestaInformationBinding, FiestaViewModel>() {

    override val viewModel: FiestaViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_fiesta_information
    private val args: FiestaInformationFragmentArgs by navArgs()

    override fun init(view: View) {

        // Get fiesta Id
        viewModel.getFiestaById(args.fiestaId)

        // Set views
        bindingObject.viewModel = viewModel
        initInfoItems()
        initContactItems()

        // Observers
        viewModel.fiesta.observe(this, Observer { bindFiesta(it) })
        viewModel.redesLink.observe(this, Observer { context?.goToBrowser(it) })
        viewModel.contacto.observe(this, Observer { checkContactoCase(it) })

    }

    private fun checkContactoCase(contactoItem: ContactoItem) {
        when(contactoItem.case) {
            ContactoCase.PHONE -> {
                context?.makePhoneCall(contactoItem.content)
            }
            ContactoCase.EMAIL -> {
                context?.sendEmail(contactoItem.content)
            }
            ContactoCase.WEB -> {
                context?.goToBrowser(contactoItem.content)
            }
        }
    }

    private fun bindFiesta(result: Result<Fiesta>) {
        when(result.status) {
            Status.LOADING -> {
                showProgress()
            }
            Status.ERROR -> {
                displayErrorInt(R.string.error2)
                Timber.e(result.message)
            }
            else -> {
                bindingObject.fiesta = result.data
                hideProgress()
            }
        }
    }

    private fun initInfoItems() {
        val adapter = FiestaInformacionAdapter()
        rv_info.apply {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = adapter
        }

        // Observer
        viewModel.informacionList.observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initContactItems() {
        val adapter = FiestaContactoAdapter(viewModel)
        rv_contacto.apply {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = adapter
        }

        // Observer
        viewModel.contactoList.observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

}