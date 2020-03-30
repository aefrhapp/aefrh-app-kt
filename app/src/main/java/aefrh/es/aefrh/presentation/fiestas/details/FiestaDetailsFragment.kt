package aefrh.es.aefrh.presentation.fiestas.details

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentFiestaDetailsBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.presentation.fiestas.FiestasViewModel
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_fiesta_details.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class FiestaDetailsFragment : BaseFragment<FragmentFiestaDetailsBinding, FiestasViewModel>() {

    override val viewModel: FiestasViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_fiesta_details

    private var safeArgs: FiestaDetailsFragmentArgs? = null

    override fun init(view: View) {

        arguments?.let { safeArgs = FiestaDetailsFragmentArgs.fromBundle(it) }
        viewModel.getFiestaById(safeArgs?.fiestaid)

        viewModel.fiesta.observe(this, Observer { result ->

            when(result.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    hideProgress()
                    Toast.makeText(context, R.string.error2, Toast.LENGTH_SHORT).show()
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


        })

    }

}