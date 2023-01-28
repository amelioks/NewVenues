package com.ameliok.newvenues.domain.repository

import com.ameliok.newvenues.data.api.model.Item

interface RestaurantRepository {
    fun getRestaurant(
        lat: Double,
        lon: Double
    ): List<Item>

    fun saveFavoriteRestaurant(
        id: String
    )

    fun removeFavoriteRestaurant(
        id: String
    )

    fun isFavoriteRestaurant(
        id: String
    ) : Boolean

}