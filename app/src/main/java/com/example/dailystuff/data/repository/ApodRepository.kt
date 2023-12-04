package com.example.dailystuff.data.repository

import com.example.dailystuff.data.models.apod.APOD
import com.example.dailystuff.data.models.apod.Response
import com.example.dailystuff.data.network.ApodDataSource

class ApodRepository {

    private val apodDataSource = ApodDataSource()
    suspend fun getAPOD(): Response<APOD> {
        try {
            val apodData = apodDataSource.getAPOD()
            if (apodData != null){
                val apod = APOD(
                   apodData.url,
                    apodData.title,
                    apodData.explanation
                )
                return Response(true, "Success", apod)
            }
        } catch (ex: Exception) {
            return Response(false, ex.message ?: "Error occurred", null)
        }
        return Response(false, "Unknown error occurred", null)
    }
}