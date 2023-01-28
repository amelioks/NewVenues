package com.ameliok.newvenues.domain

import com.ameliok.newvenues.data.api.model.Item

interface RestaurantRepository {
    suspend fun getRestaurant(
        lat: Double,
        lon: Double
    ): List<Item>
}