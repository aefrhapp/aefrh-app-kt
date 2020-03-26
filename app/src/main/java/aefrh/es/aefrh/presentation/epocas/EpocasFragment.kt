package aefrh.es.aefrh.presentation.epocas

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentEpocasBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.utils.SpanningLinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_epocas.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class EpocasFragment: BaseFragment<FragmentEpocasBinding, EpocasViewModel>() {

    override val viewModel: EpocasViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_epocas

    override fun init(view: View) {

        val adapter = EpocasListAdapter()
        rv_epocas.apply {
            this.adapter = adapter
            layoutManager = SpanningLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        viewModel.epocas.observe(viewLifecycleOwner, Observer {

            when(it.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    hideProgress()
                    Toast.makeText(context, R.string.error2, Toast.LENGTH_SHORT).show()
                    Timber.e(it.message)
                }
                else -> {
                    hideProgress()
                    val result = it.data
                    if (!result.isNullOrEmpty()) adapter.submitList(result)
                }
            }

        })


    }

}