package aefrh.es.aefrh.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_epocas.*

abstract class BaseFragment<binding : ViewDataBinding, viewModel : BaseViewModel> : Fragment() {

    protected abstract val viewModel: viewModel
    protected lateinit var bindingObject: binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingObject = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return bindingObject.root
    }

    /**
     * Get layout resource id which inflate in onCreateView.
     */
    abstract fun getLayoutResId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doDataBinding(view)

        // Handle error
        viewModel.errorStr.observe(viewLifecycleOwner, Observer { displayErrorStr(it) })
        viewModel.errorInt.observe(viewLifecycleOwner, Observer { displayErrorInt(it) })

    }

    /**
     * Do your other stuff in init after binding layout.
     */
    abstract fun init(view: View)

    private fun doDataBinding(view: View) {
        // it is extra if you want to set life cycle owner in binding
        bindingObject.lifecycleOwner = viewLifecycleOwner
        bindingObject.setVariable(BR.viewModel, viewModel)
        init(view)
    }

    fun hideProgress(){
        progress.visibility = View.GONE
    }

    fun showProgress(){
        progress.visibility = View.VISIBLE
    }

    internal fun displayErrorInt(message: Int) {
        activity?.window?.decorView?.rootView?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
        hideProgress()
    }

    internal fun displayErrorStr(message: String) {
        activity?.window?.decorView?.rootView?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
        hideProgress()
    }

}