package com.ameliok.newvenues.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ameliok.newvenues.data.RestaurantRepository

class RestaurantVenueViewModelFactory(
    private val repository: RestaurantRepository
    ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RestaurantVenueViewModel(repository) as T
        }

}
