package com.ameliok.newvenues.data.repository

import com.ameliok.newvenues.data.api.model.Item
import com.ameliok.newvenues.data.api.service.WoltVenueService
import com.ameliok.newvenues.data.preference.SharedPreferenceHelper
import com.ameliok.newvenues.domain.repository.RestaurantRepository

class RestaurantRepositoryImp(
    private val service: WoltVenueService,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : RestaurantRepository {
    override suspend fun getRestaurant(
        lat: Double,
        lon: Double
    ): List<Item> {
        return service.getWoltVenue(lat, lon).sections[1].items.take(15)
    }

    override suspend fun saveFavoriteRestaurant(id: String) {
        sharedPreferenceHelper.favoriteVenuesIds =
            sharedPreferenceHelper.favoriteVenuesIds.plus(id)
    }

    override suspend fun removeFavoriteRestaurant(id: String) {
        sharedPreferenceHelper.favoriteVenuesIds =
            sharedPreferenceHelper.favoriteVenuesIds.minus(id)
    }

    override suspend fun isFavoriteRestaurant(id: String): Boolean {
        return sharedPreferenceHelper.favoriteVenuesIds.contains(id)
    }

}