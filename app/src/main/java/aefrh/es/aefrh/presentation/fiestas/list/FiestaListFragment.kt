package aefrh.es.aefrh.presentation.fiestas.list

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentFiestaListBinding
import aefrh.es.core.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.presentation.fiestas.FiestaViewModel
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_fiesta_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class FiestaListFragment : BaseFragment<FragmentFiestaListBinding, FiestaViewModel>() {

    override val viewModel: FiestaViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_fiesta_list
    private val args: FiestaListFragmentArgs by navArgs()

    override fun init(view: View) {

        viewModel.getFiestas(args.fiestaId)

        // Init View
        val adapter = FiestasListAdapter(viewModel)
        rv_fiestas.apply {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = adapter
        }

        // On get fiestas observe
        viewModel.fiestas.observe(this, Observer {
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
        viewModel.fiestaStr.observe(this, Observer { onGoToFiestaDetail(it) })

    }

    private fun onGoToFiestaDetail(fiestaId: String) {
        val directions = FiestaListFragmentDirections.actionFragmentFiestaListToFragmentFiestaDetails(fiestaId)
        findNavController().navigate(directions)
    }

}