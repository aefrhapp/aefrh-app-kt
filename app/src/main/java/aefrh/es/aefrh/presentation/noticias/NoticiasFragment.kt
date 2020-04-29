package aefrh.es.aefrh.presentation.noticias

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentNoticiasBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_noticias.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class NoticiasFragment: BaseFragment<FragmentNoticiasBinding, NoticiasViewModel>() {

    override val viewModel: NoticiasViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_noticias

    override fun init(view: View) {

        // Get noticias
        viewModel.onGetNoticias()

        // Init View
        val adapter = NoticiasListAdapter(viewModel)
        rv_noticias.apply {
            this.adapter = adapter
        }

        // On get Noticias observe
        viewModel.noticias.observe(this, Observer {
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

        // Observe click
        viewModel.noticiaId.observe(this, Observer { onGoToNoticiaDetail(it) })

    }

    private fun onGoToNoticiaDetail(noticiaId: String) {
        val directions = NoticiasFragmentDirections.actionNoticiasFragmentToNoticiaDetailFragment(noticiaId)
        findNavController().navigate(directions)
    }

}