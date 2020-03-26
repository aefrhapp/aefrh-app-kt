package aefrh.es.aefrh.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.databinding.library.baseAdapters.BR
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
    }

    /**
     * Do your other stuff in init after binding layout.
     */
    abstract fun init(view: View)

    private fun doDataBinding(view: View) {
        // it is extra if you want to set life cycle owner in binding
        bindingObject.lifecycleOwner = viewLifecycleOwner
        init(view)
    }

    fun hideProgress(){
        progress.visibility = View.GONE
    }

    fun showProgress(){
        progress.visibility = View.VISIBLE
    }

    fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

}

//abstract class BaseFragment: Fragment() {
//
//    private lateinit var binding: ViewDataBinding
//
//    abstract fun getLayoutId(): Int
//
//    abstract fun onViewsInitialized(binding: ViewDataBinding, view: View)
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        onViewsInitialized(binding, view)
//        super.onViewCreated(view, savedInstanceState)
//    }
//
//}