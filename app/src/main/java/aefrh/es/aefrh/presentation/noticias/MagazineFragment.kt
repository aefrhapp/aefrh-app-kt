package aefrh.es.aefrh.presentation.noticias

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentMagazineBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_magazine.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MagazineFragment: BaseFragment<FragmentMagazineBinding, NoticiasViewModel>() {

    override val viewModel: NoticiasViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_magazine

    override fun init(view: View) {

        // Get magazine
        viewModel.onGetMagazine()

        // Init View
        val adapter = NoticiasListAdapter(viewModel)
        rv_magazine.apply {
            this.adapter = adapter
        }

        // On get Noticias observe
        viewModel.noticias.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    displayErrorInt(R.string.error2)
                    Timber.e(it.message)
                }
                else -> {
                    hideProgress()
                    val result = it?.data?.channel?.itemList
                    if (!result.isNullOrEmpty()) adapter.submitList(result)
                }
            }
        })

    }

}
