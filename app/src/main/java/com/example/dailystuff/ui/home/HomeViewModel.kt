package com.example.dailystuff.ui.home

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailystuff.R

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = application.getString(R.string.hello_user)
    }
    val text: LiveData<String> = _text
}