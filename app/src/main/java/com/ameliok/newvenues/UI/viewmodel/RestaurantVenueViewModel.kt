package com.ameliok.newvenues.UI.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameliok.newvenues.data.RestaurantRepository
import com.ameliok.newvenues.data.model.Item
import kotlinx.coroutines.launch

class RestaurantVenueViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {

    private val _getRestaurantResult = MutableLiveData<List<Item>>()
    val getRestaurantResult: LiveData<List<Item>>
        get() = _getRestaurantResult

    init {
        getRestaurants()
    }

    fun getRestaurants() = viewModelScope.launch {
        _getRestaurantResult.value = repository.getRestaurant(60.170187, 24.930599)
    }

    fun getRestaurantCurrentLocationData(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val result = repository.getRestaurant(latitude, longitude)
        _getRestaurantResult.value = result
        }
    }


}