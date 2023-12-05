package com.example.dailystuff.data.api.apod

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APODApiClient {

    val APODApiInstance: APODApiService by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        retrofit.create(APODApiService::class.java)
    }
}