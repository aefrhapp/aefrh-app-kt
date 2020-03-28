package aefrh.es.aefrh.presentation.nosotros

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentNosotrosBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class NosotrosFragment: BaseFragment<FragmentNosotrosBinding, NosotrosViewModel>() {

    override val viewModel: NosotrosViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_nosotros

    override fun init(view: View) {

        viewModel.interno.observe(this, Observer {

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
                }
            }

        })

    }

}