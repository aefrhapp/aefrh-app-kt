package aefrh.es.aefrh.presentation.epocas

import aefrh.es.aefrh.R
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.utils.SpanningLinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_epocas.*
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentEpocas: BaseFragment() {

    private val vModel: EpocasViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.fragment_epocas
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {

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

        vModel.epocas.observe(viewLifecycleOwner, Observer {

            when(it.status) {
                Status.LOADING -> {
                    pb_epocas.visibility = VISIBLE
                }
                Status.ERROR -> {
                    pb_epocas.visibility = GONE
                    Toast.makeText(context, R.string.error2, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    pb_epocas.visibility = GONE
                    val result = it.data
                    if (!result.isNullOrEmpty()) adapter.submitList(result)
                }
            }

        })

    }

}