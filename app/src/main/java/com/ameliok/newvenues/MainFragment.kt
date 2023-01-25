package com.ameliok.newvenues

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameliok.newvenues.databinding.FragmentMainScreenBinding
import com.ameliok.newvenues.service.ServiceBuilder
import com.ameliok.newvenues.service.WoltVenueService


class MainFragment : Fragment() {
    private val repository = RestaurantRepository(ServiceBuilder(WoltVenueService::class.java))
    private val viewModel: RestaurantVenueViewModel by viewModels {
        RestaurantVenueViewModelFactory(
            repository
        )
    }

    private val adapter = GetRestaurantAdapter()
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupView()
    }

    private fun observeViewModel() {
        viewModel.getRestaurantResult.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupView() {
        binding.restaurantRecycler.adapter = adapter
        binding.restaurantRecycler.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}