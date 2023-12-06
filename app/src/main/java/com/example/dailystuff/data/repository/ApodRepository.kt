package com.example.dailystuff.data.repository

import com.example.dailystuff.data.models.apod.APOD
import com.example.dailystuff.data.models.apod.Response
import com.example.dailystuff.data.network.ApodDataSource

class ApodRepository {

    private val apodDataSource = ApodDataSource()
    suspend fun getAPOD(): Response<APOD> {
        return try {
            val apodData = apodDataSource.getAPOD()
            if (apodData != null) {
                val apod = APOD(
                    apodData.url,
                    apodData.title,
                    apodData.explanation
                )
                Response(true, "Success", apod)
            } else {
                Response(false, "An API error Ocurred, please try again later", null)
            }
        } catch (ex: Exception) {
            Response(false, ex.message ?: "Error occurred", null)
        }
    }
}