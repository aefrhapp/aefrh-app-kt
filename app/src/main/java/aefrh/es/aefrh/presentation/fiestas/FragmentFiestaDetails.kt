package aefrh.es.aefrh.presentation.fiestas

import aefrh.es.aefrh.R
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import kotlinx.android.synthetic.main.fragment_fiesta_details.*

class FragmentFiestaDetails: BaseFragment() {

    private var safeArgs: FragmentFiestaDetailsArgs? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_fiesta_details
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {

        arguments?.let {
            safeArgs = FragmentFiestaDetailsArgs.fromBundle(it)
        }

        tv_historia.text = safeArgs?.fiestaid ?: "Nothing matter"

    }

}