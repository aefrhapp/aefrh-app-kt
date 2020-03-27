package aefrh.es.aefrh.presentation.mapa

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentMapaBinding
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import org.koin.android.viewmodel.ext.android.viewModel

class MapaFragment: BaseFragment<FragmentMapaBinding, MapaViewModel>() {

    override val viewModel: MapaViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_mapa

    override fun init(view: View) {

//        viewModel.epocas.observe(this, Observer {
//
//            when(it.status) {
//                Status.LOADING -> {
//                    showProgress()
//                }
//                Status.ERROR -> {
//                    hideProgress()
//                    Toast.makeText(context, R.string.error2, Toast.LENGTH_SHORT).show()
//                    Timber.e(it.message)
//                }
//                else -> {
//                    hideProgress()
//                    val result = it.data
//                    if (!result.isNullOrEmpty()) adapter.submitList(result)
//                }
//            }
//
//        })


    }


}
