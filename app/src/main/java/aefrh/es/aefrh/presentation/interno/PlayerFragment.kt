package aefrh.es.aefrh.presentation.interno

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentPlayerBinding
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import kotlinx.android.synthetic.main.fragment_player.*
import org.koin.android.viewmodel.ext.android.viewModel

class PlayerFragment: BaseFragment<FragmentPlayerBinding, InternoViewModel>()
//    , YouTubePlayer.OnInitializedListener
{

    override val viewModel: InternoViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_player
    private var safeArgs: PlayerFragmentArgs? = null

    override fun init(view: View) {

        arguments?.let { safeArgs =
            PlayerFragmentArgs.fromBundle(it)
        }
        tvPlayer.text = safeArgs?.videoid

        //        yt_pv.initialize(BuildConfig.YOU, this)

        // Bind viewmodel
//        bindingObject.viewModel = viewModel

//        val youTubePlayerFragment = findFragmentById(R.id.official_player_view) as YouTubePlayerSupportFragment?
//        youTubePlayerFragment?.initialize("YOUR_API_KEY", this)

    }

    //    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
//        if (!wasRestored) {
//            player?.cueVideo("wKJ9KzGQq0w");
//        }
//    }
//
//    override fun onInitializationFailure(provider: YouTubePlayer.Provider,youTubeInitializationResult: YouTubeInitializationResult) {
//        if (youTubeInitializationResult.isUserRecoverableError) {
////            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
//        } else {
//            val errorMessage = String.format(
//                "There was an error initializing the YouTubePlayer (%1\$s)",
//                youTubeInitializationResult.toString()
//            )
//            Timber.e(errorMessage)
//        }
//    }

}