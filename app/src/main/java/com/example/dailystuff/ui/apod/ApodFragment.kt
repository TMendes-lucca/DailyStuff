package com.example.dailystuff.ui.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

        observe(apodViewModel)


        apodViewModel.fetchAPOD()

        return binding.root
    }

    private fun observe(apodViewModel: ApodViewModel) {

        //Insert the image via Picasso
        apodViewModel.apodImageUrl.observe(viewLifecycleOwner) {
            apodViewModel.loadAPODImage(binding.apodImagePictureOfTheDay, it)
        }

        //Change the loading state on the application
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                apodViewModel.isLoading.collect {
                    if (it) {
                        binding.apodImagePictureOfTheDay.visibility = View.GONE
                        binding.apodTextApodTitle.visibility = View.GONE
                        binding.apodTextPictureDescription.visibility = View.GONE
                        binding.apodTextFragmentDescription.visibility = View.GONE
                        binding.apodLoadingState.visibility = View.VISIBLE
                    } else {
                        binding.apodImagePictureOfTheDay.visibility = View.VISIBLE
                        binding.apodTextApodTitle.visibility = View.VISIBLE
                        binding.apodTextFragmentDescription.visibility = View.VISIBLE
                        binding.apodTextPictureDescription.visibility = View.VISIBLE
                        binding.apodLoadingState.visibility = View.GONE

                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}