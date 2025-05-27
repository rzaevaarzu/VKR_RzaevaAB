package com.example.vkr_rzaevaab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr_rzaevaab.databinding.FragmentProfileBinding
import com.example.vkr_rzaevaab.entities.User
import com.example.vkr_rzaevaab.sharedpreferences.SharedPref

class ProfileFragment : Fragment() {

    val bundle= Bundle()
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val sharedPreference = SharedPref(requireContext())
        binding.fio.text = sharedPreference.getPreferenceString("user_fio")
        binding.birthdate.text = sharedPreference.getPreferenceString("birth_date")
        binding.telephone.text = sharedPreference.getPreferenceString("telephone")
        binding.email.text = sharedPreference.getPreferenceString("email")



        binding.exitButton.setOnClickListener{
            sharedPreference!!.clearSharedPreference()
            Toast.makeText(requireContext(),"Успешный выход из аккаунта", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_homeFragment)
        }

//        binding.editButton.setOnClickListener{
////            bundle.putString("usr", user_id.toString())
//            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_editProfileFragment)
//        }
        return binding.root
    }
}