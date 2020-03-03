package aefrh.es.aefrh.presentation.fiestas.details

import aefrh.es.aefrh.BR
import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentFiestaDetailsBinding
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.presentation.fiestas.FiestasViewModel
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_fiesta_details.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class FragmentFiestaDetails: BaseFragment() {

    private val vModel: FiestasViewModel by viewModel()
    private var safeArgs: FragmentFiestaDetailsArgs? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_fiesta_details
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {

        arguments?.let {
            safeArgs =
                FragmentFiestaDetailsArgs.fromBundle(
                    it
                )
        }

        vModel.getFiestaById(safeArgs?.fiestaid)
        vModel.fiesta.observe(viewLifecycleOwner, Observer { result ->

            val fiesta = result.data
            binding.setVariable(BR.fiesta, fiesta)
            binding.executePendingBindings()

            // Show images in slider
            slider_details.apply {
                if (fiesta != null) {
                    setItems(fiesta.imagenes.map { it.Url })
                    addTimerToSlide(5000)
                }
            }

        })

    }

}