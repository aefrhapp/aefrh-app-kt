package aefrh.es.aefrh.presentation.interno

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentMultimediaBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.fragment_multimedia.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class MultimediaFragment: BaseFragment<FragmentMultimediaBinding, InternoViewModel>(), YouTubePlayer.OnInitializedListener {

    override val viewModel: InternoViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_multimedia

    override fun init(view: View) {

//        yt_pv.initialize(BuildConfig.YOU, this)

        // Bind viewmodel
//        bindingObject.viewModel = viewModel

//        val youTubePlayerFragment = findFragmentById(R.id.official_player_view) as YouTubePlayerSupportFragment?
//        youTubePlayerFragment?.initialize("YOUR_API_KEY", this)

        // Oberve
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
                    val interno = result.data
                    interno?.imagen_video?.let { setImageRounded(it.Url, imTop) }
                    interno?.imagen_audio_visual?.let { setImageRounded(it.Url, imBottom) }
                }
            }

        })

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

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if (!wasRestored) {
            player?.cueVideo("wKJ9KzGQq0w");
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider,youTubeInitializationResult: YouTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError) {
//            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage = String.format(
                "There was an error initializing the YouTubePlayer (%1\$s)",
                youTubeInitializationResult.toString()
            )
            Timber.e(errorMessage)
        }
    }

}