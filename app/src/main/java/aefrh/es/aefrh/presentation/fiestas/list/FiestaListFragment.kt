package aefrh.es.aefrh.presentation.fiestas.list

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentFiestaListBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.presentation.fiestas.FiestasViewModel
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_fiesta_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class FiestaListFragment : BaseFragment<FragmentFiestaListBinding, FiestasViewModel>() {

    override val viewModel: FiestasViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_fiesta_list

    private var safeArgs: FiestaListFragmentArgs? = null

    override fun init(view: View) {

        arguments?.let { safeArgs = FiestaListFragmentArgs.fromBundle(it) }
        viewModel.getFiestas(safeArgs?.fiestaid)

        val adapter = FiestasListAdapter()
        rv_fiestas.apply {
            this.adapter = adapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        viewModel.fiestas.observe(this, Observer {

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