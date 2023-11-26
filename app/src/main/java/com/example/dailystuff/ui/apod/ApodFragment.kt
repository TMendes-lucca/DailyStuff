package com.example.dailystuff.ui.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dailystuff.data.api.apod.APODApiClient
import com.example.dailystuff.databinding.FragmentApodBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApodFragment : Fragment() {

    private lateinit var apodImage: ImageView
    private lateinit var apodFragmentDescription: TextView
    private lateinit var apodViewModel: ApodViewModel

    private var _binding: FragmentApodBinding? = null
    private val APODServiceInstance = APODApiClient.APODApiInstance

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        apodViewModel = ViewModelProvider(this).get(ApodViewModel::class.java)

        _binding = FragmentApodBinding.inflate(inflater, container, false)
        val root: View = binding.root

        apodImage = binding.apodImagePictureOfTheDay
        apodFragmentDescription = binding.apodTextFragmentDescription

        fetchAPOD()
        observe()

        return root
    }

    private fun observe() {
        apodViewModel.apodTitle.observe(viewLifecycleOwner) {
            apodFragmentDescription.text = it
        }

    }

    private fun fetchAPOD(){

        //Open the coroutine and make the APOD Api call
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apodResponse = APODServiceInstance.getAPOD()
                if (apodResponse.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val imageUrl = apodResponse.body()?.url
                        val imageTitle = apodResponse.body()?.title

                        //Set the components of the api call to the fragment views
                        imageUrl.let {
                            Picasso.get().load(it).into(apodImage)
                        }
                        apodViewModel.setApodTitle(imageTitle.toString())

                    }
                }
            } catch (e: Exception) {
                println("Error: $e")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}