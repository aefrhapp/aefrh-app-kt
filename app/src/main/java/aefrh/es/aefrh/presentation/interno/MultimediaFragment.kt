package aefrh.es.aefrh.presentation.interno

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentMultimediaBinding
import aefrh.es.core.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MultimediaFragment: BaseFragment<FragmentMultimediaBinding, InternoViewModel>() {

    override val viewModel: InternoViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_multimedia

    override fun init(view: View) {

        // Bind
        bindingObject.viewModel = viewModel
        viewModel.getInterno()

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

        viewModel.videoId.observe(this, Observer { goToPlayer(it) })

    }

    private fun goToPlayer(videoId: String) {
        val directions = MultimediaFragmentDirections.actionMultimediaFragmentToPlayerFragment(videoId)
        findNavController().navigate(directions)
    }

}