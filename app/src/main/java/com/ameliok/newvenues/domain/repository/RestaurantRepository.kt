package com.ameliok.newvenues.domain.repository

import com.ameliok.newvenues.data.api.model.Item

interface RestaurantRepository {
    suspend fun getRestaurant(
        lat: Double,
        lon: Double
    ): List<Item>

    suspend fun saveFavoriteRestaurant(
        id: String
    )

    suspend fun isFavoriteRestaurant(
        id: String
    ) : Boolean
}