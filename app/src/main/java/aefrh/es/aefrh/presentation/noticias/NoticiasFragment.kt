package aefrh.es.aefrh.presentation.noticias

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentNoticiasBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class NoticiasFragment: BaseFragment<FragmentNoticiasBinding, NoticiasViewModel>() {

    override val viewModel: NoticiasViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_noticias

    override fun init(view: View) {

        viewModel.noticias.observe(viewLifecycleOwner, Observer {

            when(it.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    displayErrorInt(R.string.error2)
                    Timber.e(it.message)
                }
                else -> {
                    val result = it.data
                    Timber.e(result.toString())
                }
            }

        })

    }

}