package aefrh.es.aefrh.presentation.fiesta

import aefrh.es.aefrh.R
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import kotlinx.android.synthetic.main.fragment_fiesta.*

class FragmentFiesta: BaseFragment() {

    private var safeArgs: FragmentFiestaArgs? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_fiesta
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {

        arguments?.let {
            safeArgs = FragmentFiestaArgs.fromBundle(it)
        }
        tvFiesta.text = safeArgs?.fiestaid

    }

}