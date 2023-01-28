package com.ameliok.newvenues.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameliok.newvenues.ui.recyclerview.GetRestaurantAdapter
import com.ameliok.newvenues.ui.viewmodel.RestaurantVenueViewModel
import com.ameliok.newvenues.ui.viewmodel.RestaurantVenueViewModelFactory
import com.ameliok.newvenues.data.preference.SharedPreferenceHelper
import com.ameliok.newvenues.databinding.FragmentMainScreenBinding
import com.ameliok.newvenues.data.api.service.ServiceBuilder
import com.ameliok.newvenues.data.api.service.WoltVenueService
import com.ameliok.newvenues.data.repository.RestaurantRepositoryImpl
import com.ameliok.newvenues.domain.repository.RestaurantRepository
import com.ameliok.newvenues.utils.fineAndCoarseLocationPermissionGranted
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainFragment : Fragment() {
    private val repository: RestaurantRepository by lazy {
        RestaurantRepositoryImpl(
            ServiceBuilder(WoltVenueService::class.java),
            sharedPreference
        )
    }
    private val viewModel: RestaurantVenueViewModel by viewModels {
        RestaurantVenueViewModelFactory(
            repository
        )
    }

    private val sharedPreference = SharedPreferenceHelper(requireActivity().applicationContext)
    private lateinit var adapter: GetRestaurantAdapter
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val requestLocation = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GetRestaurantAdapter(repository)
        observeViewModel()
        setupView()
    }

    private fun observeViewModel() {
        viewModel.getRestaurantResult.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupView(): View {
        binding.restaurantRecycler.adapter = adapter
        binding.restaurantRecycler.layoutManager = LinearLayoutManager(context)
        enableUserLocation()
        return binding.root
    }

    // Checks if users have given their location and sets location enabled if so.
    @SuppressLint("MissingPermission")
    fun enableUserLocation() {
        if (requireContext().fineAndCoarseLocationPermissionGranted()) {

        } else {
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
                    viewModel.getRestaurantCurrentLocationData(
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}