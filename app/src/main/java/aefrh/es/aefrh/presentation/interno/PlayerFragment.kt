package aefrh.es.aefrh.presentation.interno

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentPlayerBinding
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_player.*
import org.koin.android.viewmodel.ext.android.viewModel

class PlayerFragment: BaseFragment<FragmentPlayerBinding, InternoViewModel>() {

    override val viewModel: InternoViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_player

    override fun init(view: View) {
        arguments?.let {
            val arg = PlayerFragmentArgs.fromBundle(it)
            ytPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(arg.videoid, 0f)
                }
            })
        }
    }

}