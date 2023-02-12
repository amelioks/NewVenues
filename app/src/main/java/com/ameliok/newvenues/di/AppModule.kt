package com.ameliok.newvenues.di

import android.content.Context
import com.ameliok.newvenues.data.api.service.ServiceBuilder
import com.ameliok.newvenues.data.api.service.WoltVenueService
import com.ameliok.newvenues.data.preference.SharedPreferenceHelper
import com.ameliok.newvenues.data.repository.RestaurantRepositoryImpl
import com.ameliok.newvenues.domain.repository.RestaurantRepository
import com.ameliok.newvenues.ui.recyclerview.GetRestaurantAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object AppModule {
    @Provides
    fun provideSharedPreferenceHelper(@ApplicationContext appContext: Context): SharedPreferenceHelper {
        return SharedPreferenceHelper(appContext)
    }

    @Provides
    fun provideWoltVenueService(): WoltVenueService {
        return ServiceBuilder(WoltVenueService::class.java)
    }

    @Provides
    fun provideRestaurantRepository(woltVenueService: WoltVenueService,
        sharedPreferenceHelper: SharedPreferenceHelper
    ): RestaurantRepository {
        return RestaurantRepositoryImpl(
            woltVenueService,
            sharedPreferenceHelper
        )
    }

    @Provides
    fun provideGetRestaurantAdapter(repository: RestaurantRepository): GetRestaurantAdapter {
        return GetRestaurantAdapter(repository)
    }
}