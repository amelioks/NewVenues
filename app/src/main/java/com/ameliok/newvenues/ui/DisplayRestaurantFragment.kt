package com.ameliok.newvenues.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.ameliok.newvenues.R
import com.ameliok.newvenues.ui.viewmodel.RestaurantVenueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayRestaurantFragment : Fragment() {

    private val viewModel: RestaurantVenueViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {

            }
        }
    }
}