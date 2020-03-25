package aefrh.es.aefrh.presentation.fiestas.list

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentFiestaDetailsBinding
import aefrh.es.aefrh.databinding.FragmentFiestaListBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.presentation.fiestas.FiestasViewModel
import aefrh.es.aefrh.presentation.splash.SplashViewModel
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_epocas.*
import kotlinx.android.synthetic.main.fragment_fiesta_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class FiestaListFragment : BaseFragment<FragmentFiestaListBinding, FiestasViewModel>() {

    override val viewModel: FiestasViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_fiesta_list

//    private var safeArgs: FragmentFiestaListArgs? = null

    override fun init(view: View) {

//        arguments?.let {
//            safeArgs =
//                FragmentFiestaListArgs.fromBundle(
//                    it
//                )
//        }
//        viewModel.getFiestas(safeArgs?.fiestaid)

        val adapter = FiestasListAdapter()
        rv_fiestas.apply {
            this.adapter = adapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        viewModel.fiestas.observe(viewLifecycleOwner, Observer {

            when(it.status) {
                Status.LOADING -> {
                    pb_fiestas_list.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    pb_fiestas_list.visibility = View.GONE
                    Toast.makeText(context, R.string.error2, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    pb_fiestas_list.visibility = View.GONE
                    val result = it.data
                    if (!result.isNullOrEmpty()) adapter.submitList(result)
                }
            }

        })

    }

}