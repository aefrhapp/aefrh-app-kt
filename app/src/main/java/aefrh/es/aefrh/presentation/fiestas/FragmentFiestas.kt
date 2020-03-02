package aefrh.es.aefrh.presentation.fiestas

import aefrh.es.aefrh.R
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_fiesta.*
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentFiestas: BaseFragment() {

    private val vModel: FiestasViewModel by viewModel()
    private var safeArgs: FragmentFiestasArgs? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_fiesta
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {

        arguments?.let {
            safeArgs = FragmentFiestasArgs.fromBundle(it)
        }

        vModel.getFiestas(safeArgs?.fiestaid)

        val adapter = FiestasListAdapter()
        rv_fiestas.apply {
            this.adapter = adapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        vModel.fiestas.observe(viewLifecycleOwner, Observer {
            val result = it.data
            if (!result.isNullOrEmpty()) adapter.submitList(result)
        })

    }

}