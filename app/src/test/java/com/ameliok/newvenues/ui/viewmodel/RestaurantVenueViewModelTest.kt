package com.ameliok.newvenues.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
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
        Dispatchers.setMain(TestCoroutineDispatcher())
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
        Dispatchers.resetMain()
    }
}