package com.example.dailystuff.data.api.apod.model

import com.google.gson.annotations.SerializedName

data class APODResponse(
    val date: String,
    val explanation: String,
    @SerializedName("media_type")
    val mediaType: String,
    val title: String,
    val url: String
)
