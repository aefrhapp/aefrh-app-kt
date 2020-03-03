package aefrh.es.aefrh.presentation.fiestas.details

import aefrh.es.aefrh.R
import aefrh.es.aefrh.presentation.base.BaseFragment
import android.view.View
import androidx.databinding.ViewDataBinding
import kotlinx.android.synthetic.main.fragment_fiesta_details.*
import timber.log.Timber

class FragmentFiestaDetails: BaseFragment() {

    private var safeArgs: FragmentFiestaDetailsArgs? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_fiesta_details
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {

        arguments?.let {
            safeArgs =
                FragmentFiestaDetailsArgs.fromBundle(
                    it
                )
        }

        // add images URLs
        val images = listOf(
            "https://ac19oljbfr.files-sashido.cloud/af8f6d4a93b7d22a58761ce580135823_IMG_1578509895746.jpg",
            "https://ac19oljbfr.files-sashido.cloud/fcdfbd602ab0e57d200b0ad374c9b298_IMG_1578509907487.jpg",
            "https://ac19oljbfr.files-sashido.cloud/630b3ef64847669acc2cecdf31c25339_IMG_1578509915890.jpg")

        // Add image URLs to sliderImage
        slider_details.setItems(images)
        slider_details.addTimerToSlide(4000)
        slider_details.onPageListener(onPageScroll = { position, offSet, offSetPixels ->
            Timber.e("position $position  offSet: $offSet  pixels $offSetPixels")
        }, onPageStateChange = { state ->
            Timber.e("State change $state")
        }, onPageSelected = { position ->
            Timber.e("page select $position")
        })

//        SliderImage.openfullScreen(items = images, position = 0)

        // Add slider component to a container
//        container_main_images.addView(slider)

//        tv_historia.text = safeArgs?.fiestaid ?: "Nothing matter"

    }

}