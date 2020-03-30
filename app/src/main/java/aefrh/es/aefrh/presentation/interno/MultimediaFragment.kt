package aefrh.es.aefrh.presentation.interno

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentMultimediaBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_multimedia.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class MultimediaFragment: BaseFragment<FragmentMultimediaBinding, InternoViewModel>() {

    override val viewModel: InternoViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_multimedia

    override fun init(view: View) {

        // Bind
        bindingObject.viewModel = viewModel

        viewModel.interno.observe(this, Observer { result ->

            when(result.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    hideProgress()
                    Toast.makeText(context, R.string.error2, Toast.LENGTH_SHORT).show()
                    Timber.e(result.message)
                }
                else -> {
                    hideProgress()

                    // Binding
                    val interno = result.data
                    bindingObject.interno = interno

                    // Observers
                    interno?.imagen_video?.let { setImageRounded(it.Url, imTop) }
                    interno?.imagen_audio_visual?.let { setImageRounded(it.Url, imBottom) }
                }
            }

        })

        viewModel.videoCase.observe(this, Observer { goToReproductor(it) })

    }

    private fun goToReproductor(videoId: String) {
        val directions = MultimediaFragmentDirections.actionMultimediaFragmentToReproductorFragment(videoId)
        findNavController().navigate(directions)
    }

    private fun setImageRounded(url: String, imageView: ImageView) {
        context?.let {
            Glide.with(it)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(24)))
                .into(imageView)
        }
    }

}