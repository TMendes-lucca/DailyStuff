package com.example.dailystuff.data.api.apod

import com.example.dailystuff.data.api.apod.model.APODResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface APODApiService {

    @GET("apod")
    suspend fun getAPOD(
        @Query("api_key") apiKey: String = "zetBCo8oehkAixRHbj2E4KMg18S49Jpsyd3KVT3L",
    ): Response<APODResponse>
}