package com.ameliok.newvenues.data.api.service

import com.ameliok.newvenues.data.api.response.GetRestaurantsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WoltVenueService {

    @GET("v1/pages/restaurants")
    suspend fun getWoltVenue(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ) : GetRestaurantsResponse

}