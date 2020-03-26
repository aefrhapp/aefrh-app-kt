package aefrh.es.aefrh.presentation.fiestas.details

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentFiestaDetailsBinding
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.presentation.fiestas.FiestasViewModel
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_fiesta_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class FiestaDetailsFragment : BaseFragment<FragmentFiestaDetailsBinding, FiestasViewModel>() {

    override val viewModel: FiestasViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_fiesta_details

    private var safeArgs: FiestaDetailsFragmentArgs? = null

    override fun init(view: View) {

        arguments?.let { safeArgs = FiestaDetailsFragmentArgs.fromBundle(it) }
        viewModel.getFiestaById(safeArgs?.fiestaid)

        viewModel.fiesta.observe(viewLifecycleOwner, Observer { result ->

            val fiesta = result.data
            bindingObject.fiesta = fiesta

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