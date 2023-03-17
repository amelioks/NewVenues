package com.ameliok.newvenues.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestaurantVenueViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var restaurantVenueViewModel: RestaurantVenueViewModel

    @Before
    fun setupViewModel() {
        restaurantVenueViewModel = RestaurantVenueViewModel(
            ApplicationProvider.getApplicationContext()
        )
    }



}