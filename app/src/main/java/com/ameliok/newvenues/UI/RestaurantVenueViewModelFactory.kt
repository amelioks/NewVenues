package com.ameliok.newvenues.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ameliok.newvenues.domain.RestaurantRepository

class RestaurantVenueViewModelFactory(
    private val repository: RestaurantRepository
    ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RestaurantVenueViewModel(repository) as T
        }

}
