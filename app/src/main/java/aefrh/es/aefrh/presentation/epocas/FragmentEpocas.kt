package aefrh.es.aefrh.presentation.epocas

import aefrh.es.aefrh.R
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.utils.SpanningLinearLayoutManager
import android.view.View
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
            val result = it.data
            if (!result.isNullOrEmpty()) adapter.submitList(result)
        })

    }

}