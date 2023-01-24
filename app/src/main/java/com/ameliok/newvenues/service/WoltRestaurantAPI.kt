package com.ameliok.newvenues.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {

    operator fun <T> invoke(serviceClass: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_ENDPOINT)
            .build()
            .create(serviceClass)

    }
    private const val BASE_ENDPOINT = "https://restaurant-api.wolt.com/"
}