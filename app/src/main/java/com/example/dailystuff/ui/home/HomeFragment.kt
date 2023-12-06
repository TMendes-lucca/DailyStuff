package com.example.dailystuff.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dailystuff.R
import com.example.dailystuff.databinding.FragmentHomeBinding
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var cardApod: MaterialCardView
    private lateinit var textWelcome: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set the lateinit variables
        cardApod = binding.homeCardApod
        textWelcome = binding.homeTextWelcome

        //Navigate to APOD fragment
        cardApod.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_apod)
        }

        observe(homeViewModel)

        return root
    }

    private fun observe(homeViewModel: HomeViewModel) {
        homeViewModel.welcomeText.observe(viewLifecycleOwner) {
            textWelcome.text = it
        }
        homeViewModel.whatWouldYouLikeText.observe(viewLifecycleOwner) {
            binding.homeTextSubtext.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}