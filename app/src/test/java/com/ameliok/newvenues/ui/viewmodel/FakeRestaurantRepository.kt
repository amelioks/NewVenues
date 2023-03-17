package com.ameliok.newvenues.ui.viewmodel

import com.ameliok.newvenues.domain.model.ItemDomain
import com.ameliok.newvenues.domain.repository.RestaurantRepository

class FakeRestaurantRepository: RestaurantRepository {
    val fakeData = listOf<ItemDomain>()

    override suspend fun getRestaurant(lat: Double, lon: Double): List<ItemDomain> {
        TODO("Not yet implemented")
    }

    override fun saveFavoriteRestaurant(id: String) {
        TODO("Not yet implemented")
    }

    override fun removeFavoriteRestaurant(id: String) {
        TODO("Not yet implemented")
    }

    override fun isFavoriteRestaurant(id: String): Boolean {
        TODO("Not yet implemented")
    }
}