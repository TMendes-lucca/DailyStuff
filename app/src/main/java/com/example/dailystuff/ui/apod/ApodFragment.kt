package com.example.dailystuff.ui.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dailystuff.R
import com.example.dailystuff.data.api.apod.APODApiClient
import com.example.dailystuff.databinding.FragmentApodBinding
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApodFragment : Fragment() {


    private var _binding: FragmentApodBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val apodViewModel = ViewModelProvider(this)[ApodViewModel::class.java]
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_apod, container, false)
        binding.vm = apodViewModel
        binding.lifecycleOwner = this

        apodViewModel.fetchAPOD()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}