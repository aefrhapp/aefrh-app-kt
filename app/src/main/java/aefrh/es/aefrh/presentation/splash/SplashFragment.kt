package aefrh.es.aefrh.presentation.splash

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentSplashBinding
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_splash

    override fun init(view: View) {
        viewModel.isCurrentUser.observe(this, Observer {
            if(it) {
                goToHome()
            }
        })
    }

    private fun goToHome() {
        val directions = SplashFragmentDirections.actionSplashFragmentToFragmentEpocas()
        findNavController().navigate(directions)
    }

}