package aefrh.es.aefrh.presentation.mapa

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentMapaBinding
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.utils.getEpocaIcon
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MapaFragment: BaseFragment<FragmentMapaBinding, MapaViewModel>(), OnMapReadyCallback {

    override val viewModel: MapaViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_mapa

    private lateinit var mMap: GoogleMap
    private val args: MapaFragmentArgs by navArgs()

    override fun init(view: View) {

        // Set map
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Get id
        if(args.fiestaId == GET_ALL) {
            viewModel.onGetAllFiestas()
            onObserveAllFiestas()
        } else {
            viewModel.onGetSingleFiesta(args.fiestaId)
            onObserveSingleFiesta()
        }

    }

    private fun onObserveSingleFiesta() {
        viewModel.fiesta.observe(this, Observer {

            when(it.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    displayErrorInt(R.string.error2)
                    Timber.e(it.message)
                }
                else -> {
                    hideProgress()
                    val fiesta = it.data
                    if(fiesta != null) {
                        mMap.addMarker(
                            MarkerOptions().position(LatLng(fiesta.localizacion.latitude, fiesta.localizacion.longitude))
                                .title(String.format("%s\n%s", fiesta.nombre, fiesta.id))
                                .snippet(fiesta.epoca)
                                .icon(BitmapDescriptorFactory.fromResource(getEpocaIcon(fiesta.epoca)))
                        )
                    }
                }
            }

        })

    }

    private fun onObserveAllFiestas() {
        viewModel.fiestas.observe(this, Observer {

            when(it.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    displayErrorInt(R.string.error2)
                    Timber.e(it.message)
                }
                else -> {
                    hideProgress()
                    val result = it.data
                    result?.forEach { fiesta ->
                        mMap.addMarker(
                            MarkerOptions().position(LatLng(fiesta.localizacion.latitude, fiesta.localizacion.longitude))
                                .title(String.format("%s\n%s", fiesta.nombre, fiesta.id))
                                .snippet(fiesta.epoca)
                                .icon(BitmapDescriptorFactory.fromResource(getEpocaIcon(fiesta.epoca)))
                        )
                    }
                }
            }

        })

    }

    override fun onMapReady(googleMap: GoogleMap?) {

        // Set map
        mMap = googleMap!!

        // Set map style
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style_json))
        mMap.uiSettings.isMapToolbarEnabled = false
        mMap.uiSettings.isZoomControlsEnabled = false

        // Set info window
        mMap.setInfoWindowAdapter(context?.let { MarkerInfoView(it) })
        if(args.fiestaId == GET_ALL) {
            mMap.setOnInfoWindowClickListener { marker ->
                val fiestaId = marker.title?.substringAfter('\n').toString()
                val directions = MapaFragmentDirections.actionMapaFragmentToFiestaDetailsfragment(fiestaId)
                findNavController().navigate(directions)
            }
        }

        // Center map in Spain
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(40.3752455, -3.2475477))
            .zoom(5.5f)
            .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }

    companion object {
        const val GET_ALL: String = "GET_ALL"
    }

}