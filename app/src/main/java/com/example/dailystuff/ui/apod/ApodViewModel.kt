package com.example.dailystuff.ui.apod

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailystuff.data.repository.ApodRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApodViewModel : ViewModel() {

    private val apodRepository = ApodRepository()

    //Variables that are shown in the screen

    private var _apodImageUrl = MutableLiveData<String>()
    val apodImageUrl: LiveData<String> = _apodImageUrl

    private var _apodTitle = MutableLiveData("Image Title")
    val apodTitle: LiveData<String> = _apodTitle

    private var _apodDescription = MutableLiveData("Image Description")
    val apodDescription: LiveData<String> = _apodDescription

    //Loading State

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    // Possible Errors
    private var _hasErrorOcurred = MutableStateFlow(false)
    val hasErrorOcurred: StateFlow<Boolean> = _hasErrorOcurred

    private var _errorMessage = MutableLiveData("Error Message")
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchAPOD(){
        _isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = apodRepository.getAPOD()
            if (response.success){
                _apodImageUrl.postValue(response.data?.imageUrl)
                _apodTitle.postValue(response.data?.title)
                _apodDescription.postValue(response.data?.description)
                _hasErrorOcurred.value = false
            } else {
                _hasErrorOcurred.value = true
                _errorMessage.postValue(response.message)
            }
            _isLoading.value = false
        }
    }

    fun loadAPODImage(imageView: ImageView, imageUrl: String){
        imageUrl.let{
            Picasso.get().load(it).into(imageView)
        }
    }
}