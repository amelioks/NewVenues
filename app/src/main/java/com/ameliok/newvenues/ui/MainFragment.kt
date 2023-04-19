package com.ameliok.newvenues.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ameliok.newvenues.R
import com.ameliok.newvenues.databinding.FragmentMainBinding
import com.ameliok.newvenues.ui.recyclerview.GetRestaurantAdapter
import com.ameliok.newvenues.ui.viewmodel.RestaurantVenueViewModel
import com.ameliok.newvenues.utils.fineAndCoarseLocationPermissionGranted
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment() {
    @Inject lateinit var adapter: GetRestaurantAdapter
    private val viewModel: RestaurantVenueViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val requestLocation = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        sharedPreference = SharedPreferenceHelper(requireActivity().applicationContext)
//        repository = RestaurantRepositoryImpl(
//            ServiceBuilder(WoltVenueService::class.java),
//            sharedPreference
//        )
//        adapter = GetRestaurantAdapter(repository)
        observeViewModel()
        setupView()
        enableUserLocation()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.getRestaurantResult.collect { newList ->
                adapter.submitList(newList)
            }
        }
    }

    private fun setupView() {
        binding.restaurantRecycler.adapter = adapter
        binding.restaurantRecycler.layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.layer, null)
            ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
        binding.restaurantRecycler.addItemDecoration(dividerItemDecoration)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}