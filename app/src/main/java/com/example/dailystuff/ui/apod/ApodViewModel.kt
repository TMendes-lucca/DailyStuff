package com.example.dailystuff.ui.apod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailystuff.data.repository.ApodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApodViewModel : ViewModel() {

    private val apodRepository = ApodRepository()

    private var _apodImage = MutableLiveData<String>()
    val apodImage: LiveData<String> = _apodImage

    private var _apodTitle = MutableLiveData("Image Title")
    val apodTitle: LiveData<String> = _apodTitle

    private var _apodDescription = MutableLiveData("Image Description")
    val apodDescription: LiveData<String> = _apodDescription

    private var _hasErrorOcurred = MutableStateFlow(false)
    val hasErrorOcurred: StateFlow<Boolean> = _hasErrorOcurred

    private var _errorMessage = MutableLiveData("Error Message")
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchAPOD(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = apodRepository.getAPOD()
            if (response.success){
                _apodImage.postValue(response.data?.imageUrl)
                _apodTitle.postValue(response.data?.title)
                _apodDescription.postValue(response.data?.description)
                _hasErrorOcurred.value = false
            } else {
                _hasErrorOcurred.value = true
                _errorMessage.postValue(response.message)
            }
        }
    }
}