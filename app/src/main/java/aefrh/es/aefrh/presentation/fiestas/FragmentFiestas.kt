package aefrh.es.aefrh.presentation.fiestas

import aefrh.es.aefrh.R
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import androidx.databinding.ViewDataBinding

class FragmentFiestas: BaseFragment() {

    private var safeArgs: FragmentFiestasArgs? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_fiesta
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {

        arguments?.let {
            safeArgs = FragmentFiestasArgs.fromBundle(it)
        }
//        tvFiesta.text = safeArgs?.fiestaid

    }

}