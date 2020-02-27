package aefrh.es.aefrh.presentation.epocas

import aefrh.es.aefrh.R
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_epocas.*

class FragmentEpocas: BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_epocas
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {

        btEpocas.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("FIESTA_ID", "Este es el valor de 1B")

            findNavController().navigate(
                R.id.action_fragmentEpocas_to_fragmentFiesta,
                bundle)

        }

    }

}