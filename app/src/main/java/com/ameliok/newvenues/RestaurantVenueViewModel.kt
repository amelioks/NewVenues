package com.ameliok.newvenues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ameliok.newvenues.data.response.GetRestaurantsResponse

class RestaurantVenueViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {

    private val _getRestaurantResult = MutableLiveData<GetRestaurantsResponse>()
    val getRestaurantResult: LiveData<GetRestaurantsResponse> get() = _getRestaurantResult
}
