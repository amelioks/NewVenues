package com.ameliok.newvenues.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.ameliok.newvenues.R
import com.ameliok.newvenues.ui.compose.ListOfRestaurantDisplay
import com.ameliok.newvenues.ui.viewmodel.RestaurantVenueViewModel
import com.ameliok.newvenues.utils.fineAndCoarseLocationPermissionGranted
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayRestaurantFragment : Fragment() {

    private val viewModel: RestaurantVenueViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val requestLocation = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                val dataState = viewModel.getRestaurantResult.collectAsState()
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ListOfRestaurantDisplay(
                        item = dataState.value
                    )
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun enableUserLocation() {
        if (requireContext().fineAndCoarseLocationPermissionGranted()) {
            getRestaurantLocation()
        } else {
            viewModel.getDefaultRestaurants()
            requestPermissions(
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                requestLocation
            )
        }
    }

    @SuppressLint("MissingPermission")
    fun getRestaurantLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    viewModel.getRestaurantsFromLocation(
                        location.latitude,
                        location.longitude
                    )
                } else {
                    Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
                }
            }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            requestLocation -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    // permission was granted.
                    getRestaurantLocation()
                } else {
                    // permission denied.
                    // tell the user the action is cancelled
                    Toast.makeText(context, "Permission is not granted!", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}