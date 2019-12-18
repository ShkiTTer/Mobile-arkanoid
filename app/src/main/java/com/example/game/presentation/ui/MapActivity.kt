package com.example.game.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.game.R
import com.example.game.domain.entity.UserResult
import com.example.game.presentation.common.PermissionHelper
import com.example.game.presentation.viewmodel.MapViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var mMap: GoogleMap
    private val permissionHelper: PermissionHelper by lazy { PermissionHelper(this) }
    private val mapViewModel: MapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        permissionHelper.requestPermissions(
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_CODE
        )

        mapViewModel.results.observe(this, Observer {
            it ?: return@Observer
            setupMarkers(it)
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.dark))

        mapViewModel.getResults()

        if (permissionHelper.checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            mMap.isMyLocationEnabled = true
            mMap.uiSettings.isMyLocationButtonEnabled = true
        }
    }

    private fun setupMarkers(results: List<UserResult>) {
        results.forEach {
            val options = MarkerOptions()
                .position(LatLng(it.latitude, it.longitude))
                .title(getString(R.string.score, it.score))

            mMap.addMarker(options)
        }
    }
}
