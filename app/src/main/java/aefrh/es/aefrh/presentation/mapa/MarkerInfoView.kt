package aefrh.es.aefrh.presentation.mapa

import aefrh.es.aefrh.R
import android.app.Activity
import android.content.Context
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.custom_info_window.view.*
import kotlinx.android.synthetic.main.item_epoca.view.tvName

class MarkerInfoView(val context: Context) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker?): View? {
        val view = (context as Activity).layoutInflater.inflate(R.layout.custom_info_window, null)
        view.tvName.text = marker?.title?.substringBefore('\n')
        view.tvEpoca.text = marker?.snippet
        return view
    }

    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }
}