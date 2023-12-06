package com.example.dailystuff.data.network

import com.example.dailystuff.data.api.apod.APODApiClient
import com.example.dailystuff.data.api.apod.model.APODResponse

class ApodDataSource {

    private val APODServiceInstance = APODApiClient.APODApiInstance

    suspend fun getAPOD(): APODResponse? {
        val apodResponse = APODServiceInstance.getAPOD()

        return apodResponse.body()
    }

}