package aefrh.es.aefrh.presentation.fiestas.details

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentFiestaDetailsBinding
import aefrh.es.core.domain.Fiesta
import aefrh.es.core.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.presentation.fiestas.FiestaViewModel
import aefrh.es.core.utils.Result
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_fiesta_details.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class FiestaDetailsFragment : BaseFragment<FragmentFiestaDetailsBinding, FiestaViewModel>() {

    override val viewModel: FiestaViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_fiesta_details
    private val args: FiestaDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun init(view: View) {
        viewModel.getFiestaById(args.fiestaId)
        viewModel.fiesta.observe(this, Observer { bindFiesta(it) })
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
                hideProgress()

                val fiesta = result.data
                bindingObject.fiesta = fiesta

                // Show images in slider
                slider_details.apply {
                    if (fiesta != null) {
                        setItems(fiesta.imagenes.map { it.url })
                        addTimerToSlide(5000)
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_fiesta, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_info -> {
                onGoToInfo()
                true
            }
            R.id.action_map -> {
                onGoToMap()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onGoToInfo() {
        val directions = FiestaDetailsFragmentDirections.actionFiestaDetailsfragmentToFiestaInformationFragment(args.fiestaId)
        findNavController().navigate(directions)
    }

    private fun onGoToMap() {
        val directions = FiestaDetailsFragmentDirections.actionFiestaDetailsFragmentToMapaFragment(args.fiestaId)
        findNavController().navigate(directions)
    }

}