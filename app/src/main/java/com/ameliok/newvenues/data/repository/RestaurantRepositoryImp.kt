package com.ameliok.newvenues.data.repository

import com.ameliok.newvenues.data.api.model.Item
import com.ameliok.newvenues.data.api.service.WoltVenueService
import com.ameliok.newvenues.domain.RestaurantRepository

class RestaurantRepositoryImp(
    private val service: WoltVenueService
) : RestaurantRepository {
    override suspend fun getRestaurant(
        lat: Double,
        lon: Double
    ): List<Item> {
        return service.getWoltVenue(lat, lon).sections[1].items.take(15)
    }

}