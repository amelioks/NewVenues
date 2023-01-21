package com.ameliok.newvenues

import com.ameliok.newvenues.data.response.GetRestaurantsResponse
import com.ameliok.newvenues.service.WoltVenueService

class RestaurantRepository(
    private val service: WoltVenueService
) {
    suspend fun getRestaurant(
        lat: Double,
        lon: Double
    ): GetRestaurantsResponse {
        return service.getWoltVenue(lat, lon)
    }

}