package com.ameliok.newvenues.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


object ServiceBuilder {

    operator fun <T> invoke(serviceClass: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_ENDPOINT)
            .build()
            .create(serviceClass)

    }
    private const val BASE_ENDPOINT = "https://restaurant-api.wolt.com/"
}