package com.example.dailystuff.ui.apod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ApodViewModel : ViewModel() {

    private val _apodTitle = MutableLiveData<String>().apply {
        value = "Image Title"
    }

    private val _apodDescription = MutableLiveData<String>()


    fun setApodTitle(title: String){
        _apodTitle.value = title
    }

    fun setApodDescription(description: String){
        _apodDescription.value = description
    }

    val apodTitle: LiveData<String> = _apodTitle
    val apodDescription: LiveData<String> = _apodDescription
}