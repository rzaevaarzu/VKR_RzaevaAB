package com.example.vkr_rzaevaab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.vkr_rzaevaab.databinding.FragmentHomeBinding
import com.example.vkr_rzaevaab.databinding.FragmentProfileBinding
import com.example.vkr_rzaevaab.sharedpreferences.SharedPref

class HomeFragment : Fragment() {

    val bundle= Bundle()
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

}