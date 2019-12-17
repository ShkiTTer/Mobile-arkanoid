package com.example.game.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.game.R
import com.example.game.presentation.common.PermissionHelper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var mMap: GoogleMap
    private val permissionHelper: PermissionHelper by lazy { PermissionHelper(this) }

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
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (permissionHelper.checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            mMap.isMyLocationEnabled = true
            mMap.uiSettings.isMyLocationButtonEnabled = true
        }
    }
}
