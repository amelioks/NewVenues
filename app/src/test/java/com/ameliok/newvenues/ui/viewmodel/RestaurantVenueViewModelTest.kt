package com.ameliok.newvenues.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestaurantVenueViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var restaurantVenueViewModel: RestaurantVenueViewModel
    private lateinit var restaurantRepository: FakeRestaurantRepository

    @Before
    fun setupViewModel() {
        restaurantRepository = FakeRestaurantRepository()
        restaurantVenueViewModel = RestaurantVenueViewModel(
            restaurantRepository
        )
    }

    @Test
    fun getRestaurantsFromLocation_showsRestaurantList() = runBlockingTest {
        // when
        restaurantVenueViewModel.getRestaurantsFromLocation(
            12.0, 12.0
        )
        advanceUntilIdle()

        // then
        assertEquals(
            FakeRestaurantRepository.defaultRestaurant,
            restaurantVenueViewModel.getRestaurantResult.value
        )
    }
}