package com.example.vkr_rzaevaab

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.vkr_rzaevaab.databinding.ActivityMainBinding
import com.example.vkr_rzaevaab.sharedpreferences.SharedPref

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedPref by lazy { SharedPref(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

//        binding.bottomNav.setupWithNavController(navController)


        binding.bottomNav.setOnItemSelectedListener { item ->
            val bundle = Bundle()

            when (item.itemId) {
                R.id.profileFragment -> {
                    if (sharedPref.getPreferenceString("login_status") != null) {
                        Navigation.findNavController(this, R.id.nav_host_fragment)
                            .navigate(R.id.action_global_profileFragment)
                    } else {
                        bundle.putString("nav", "toProfile")
                        Navigation.findNavController(this, R.id.nav_host_fragment)
                            .navigate(R.id.action_homeFragment_to_authorizationFragment, bundle)
                        Toast.makeText(this, "Пожалуйста, выполните вход.", Toast.LENGTH_SHORT).show()
                    }
                    true
                }

                R.id.deviceListFragment -> {
                    if (sharedPref.getPreferenceString("login_status") != null) {
                        Navigation.findNavController(this, R.id.nav_host_fragment)
                            .navigate(R.id.action_global_deviceListFragment)
                    } else {
                        bundle.putString("nav", "toDeviceList")
                        Navigation.findNavController(this, R.id.nav_host_fragment)
                            .navigate(R.id.action_homeFragment_to_authorizationFragment, bundle)
                        Toast.makeText(this, "Пожалуйста, выполните вход.", Toast.LENGTH_SHORT).show()
                    }
                    true
                }


                R.id.eventListFragment -> {
                    if (sharedPref.getPreferenceString("login_status") != null) {
                        Navigation.findNavController(this, R.id.nav_host_fragment)
                            .navigate(R.id.action_global_eventListFragment)
                    } else {
                        bundle.putString("nav", "toEventList")
                        Navigation.findNavController(this, R.id.nav_host_fragment)
                            .navigate(R.id.action_homeFragment_to_authorizationFragment, bundle)
                        Toast.makeText(this, "Пожалуйста, выполните вход.", Toast.LENGTH_SHORT).show()
                    }
                    true
                }

                R.id.homeFragment -> {
                    if (sharedPref.getPreferenceString("login_status") != null) {
                        Navigation.findNavController(this, R.id.nav_host_fragment)
                            .navigate(R.id.action_global_homeFragment)
                    } else {
                        bundle.putString("nav", "toHome")
                        Navigation.findNavController(this, R.id.nav_host_fragment)
                            .navigate(R.id.action_homeFragment_to_authorizationFragment, bundle)
                        Toast.makeText(this, "Пожалуйста, выполните вход.", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                // аналогично для других пунктов меню...
                else -> false
            }
        }

    }
}