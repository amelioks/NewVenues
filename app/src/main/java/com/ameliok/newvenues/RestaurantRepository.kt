package com.ameliok.newvenues

import com.ameliok.newvenues.data.model.Item
import com.ameliok.newvenues.service.WoltVenueService

class RestaurantRepository(
    private val service: WoltVenueService
) {
    suspend fun getRestaurant(
        lat: Double,
        lon: Double
    ): List<Item> {
        return service.getWoltVenue(lat, lon).sections[1].items
    }

}