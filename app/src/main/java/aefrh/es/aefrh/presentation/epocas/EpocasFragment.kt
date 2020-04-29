package aefrh.es.aefrh.presentation.epocas

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentEpocasBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.utils.SpanningLinearLayoutManager
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_epocas.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class EpocasFragment: BaseFragment<FragmentEpocasBinding, EpocasViewModel>() {

    override val viewModel: EpocasViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_epocas

    override fun init(view: View) {

        // Init View
        val adapter = EpocasListAdapter(viewModel)
        rv_epocas.apply {
            layoutManager = SpanningLinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = adapter
        }

        // On get epocas observe
        viewModel.epocas.observe(this, Observer {
            when(it.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    displayErrorInt(R.string.error2)
                    Timber.e(it.message)
                }
                else -> {
                    hideProgress()
                    val result = it.data
                    if (!result.isNullOrEmpty()) adapter.submitList(result)
                }
            }
        })

        // Observe click
        viewModel.epoca.observe(this, Observer { onGoToFiestasByEppoca(it) })

    }

    private fun onGoToFiestasByEppoca(epocaId: String) {
        val directions = EpocasFragmentDirections.actionFragmentEpocasToFragmentFiestaList(epocaId)
        findNavController().navigate(directions)
    }

}