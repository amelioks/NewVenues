package com.ameliok.newvenues.data.repository

import com.ameliok.newvenues.domain.model.ItemDomain
import com.ameliok.newvenues.data.api.service.WoltVenueService
import com.ameliok.newvenues.data.preference.SharedPreferenceHelper
import com.ameliok.newvenues.domain.repository.RestaurantRepository
import com.ameliok.newvenues.utils.toDomainItem

class RestaurantRepositoryImpl(
    private val service: WoltVenueService,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : RestaurantRepository {
    override suspend fun getRestaurant(
        lat: Double,
        lon: Double
    ): List<ItemDomain> {
        return service.getWoltVenue(lat, lon).sections[1].items.take(15).map{
            it.toDomainItem()
        }
    }

    override fun saveFavoriteRestaurant(id: String) {
        sharedPreferenceHelper.favoriteVenuesIds =
            sharedPreferenceHelper.favoriteVenuesIds.plus(id)
    }

    override fun removeFavoriteRestaurant(id: String) {
        sharedPreferenceHelper.favoriteVenuesIds =
            sharedPreferenceHelper.favoriteVenuesIds.minus(id)
    }

    override fun isFavoriteRestaurant(id: String): Boolean {
        return sharedPreferenceHelper.favoriteVenuesIds.contains(id)
    }

}