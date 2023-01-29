package com.ameliok.newvenues.domain.repository

import com.ameliok.newvenues.domain.model.ItemDomain

interface RestaurantRepository {
    suspend fun getRestaurant(
        lat: Double,
        lon: Double
    ): List<ItemDomain>

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