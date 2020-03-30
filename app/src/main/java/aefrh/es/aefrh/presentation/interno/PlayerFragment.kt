package aefrh.es.aefrh.presentation.interno

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentPlayerBinding
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import kotlinx.android.synthetic.main.fragment_player.*
import org.koin.android.viewmodel.ext.android.viewModel

class PlayerFragment: BaseFragment<FragmentPlayerBinding, InternoViewModel>() {

    override val viewModel: InternoViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_player
    private var safeArgs: PlayerFragmentArgs? = null

    override fun init(view: View) {
        arguments?.let { safeArgs =
            PlayerFragmentArgs.fromBundle(it)
        }
        tvPlayer.text = safeArgs?.videoid
    }

}