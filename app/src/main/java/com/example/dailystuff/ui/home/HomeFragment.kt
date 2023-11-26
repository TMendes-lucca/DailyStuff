package com.example.dailystuff.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dailystuff.R
import com.example.dailystuff.databinding.FragmentHomeBinding
import com.example.dailystuff.ui.apod.ApodFragment
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var cardApod: MaterialCardView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set the lateinit variables
        cardApod = binding.homeCardApod


        //Navigate to APOD fragment
        cardApod.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.nav_apod)
        }

        val textView: TextView = binding.homeTextWelcome
        homeViewModel.welcomeText.observe(viewLifecycleOwner) {
            textView.text = it
        }
        homeViewModel.whatWouldYouLikeText.observe(viewLifecycleOwner) {
            binding.homeTextSubtext.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}