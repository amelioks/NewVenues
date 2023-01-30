package com.ameliok.newvenues.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameliok.newvenues.domain.model.ItemDomain
import com.ameliok.newvenues.domain.repository.RestaurantRepository
import kotlinx.coroutines.launch

class RestaurantVenueViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {

    private val _getRestaurantResult = MutableLiveData<List<ItemDomain>>()
    val getRestaurantResult: LiveData<List<ItemDomain>>
        get() = _getRestaurantResult

    init {
        getDefaultRestaurants()
    }

    private fun getDefaultRestaurants() {
        getRestaurantsFromLocation(HELSINKI_LATITUDE, HELSINKI_LONGITUDE)
    }


    fun getRestaurantsFromLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val result = repository.getRestaurant(latitude, longitude)
        _getRestaurantResult.value = result
        }
    }

    companion object {
        const val HELSINKI_LATITUDE = 60.170187
        const val HELSINKI_LONGITUDE = 24.930599
    }


}
