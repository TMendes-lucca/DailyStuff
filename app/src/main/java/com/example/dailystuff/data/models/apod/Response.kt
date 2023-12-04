package com.example.dailystuff.data.models.apod

data class Response<out T>(
    val success: Boolean,
    val message: String,
    val data: T?
)
