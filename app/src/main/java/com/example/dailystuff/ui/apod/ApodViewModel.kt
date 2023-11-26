package com.example.dailystuff.ui.apod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ApodViewModel : ViewModel() {

    private val _apodTitle = MutableLiveData<String>().apply {
        value = "Image Title"
    }

    val apodTitle: LiveData<String> = _apodTitle

    fun setApodTitle(title: String){
        _apodTitle.value = title
    }
}