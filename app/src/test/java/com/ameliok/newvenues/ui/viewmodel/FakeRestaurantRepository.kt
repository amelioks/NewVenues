package com.ameliok.newvenues.ui.viewmodel

import com.ameliok.newvenues.data.api.model.Venue
import com.ameliok.newvenues.domain.model.ImageDomain
import com.ameliok.newvenues.domain.model.ItemDomain
import com.ameliok.newvenues.domain.model.VenueDomain
import com.ameliok.newvenues.domain.repository.RestaurantRepository

class FakeRestaurantRepository : RestaurantRepository {
    private val fakeRestaurantList = listOf(
        ItemDomain(
            ImageDomain("https://example.com/image1.jpg"),
            VenueDomain(
                "restaurant1",
                "restaurantABC",
                "asian"
            ),
            true
        ),
        ItemDomain(
            ImageDomain("https://example.com/image2.jpg"),
            VenueDomain(
                "restaurant2",
                "restaurantDEF",
                "american"
            ),
            false
        ),
        ItemDomain(
            ImageDomain("https://example.com/image3.jpg"),
            VenueDomain(
                "restaurant3",
                "restaurantGHI",
                "africam"
            ),
            true
        )
    )

    private val favoriteRestaurant = mutableSetOf<String>()

    override suspend fun getRestaurant(lat: Double, lon: Double): List<ItemDomain> {
        return fakeRestaurantList
    }

    override fun saveFavoriteRestaurant(id: String) {
        favoriteRestaurant.add(id)
    }

    override fun removeFavoriteRestaurant(id: String) {
        favoriteRestaurant.remove(id)
    }

    override fun isFavoriteRestaurant(id: String): Boolean {
        return favoriteRestaurant.contains(id)
    }
}