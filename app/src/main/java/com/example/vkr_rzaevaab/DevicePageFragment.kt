package com.example.vkr_rzaevaab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.vkr_rzaevaab.api.viewmodels.DeviceReservViewModel
import com.example.vkr_rzaevaab.api.viewmodels.DeviceViewModel
import com.example.vkr_rzaevaab.api.viewmodels.EventRegistrViewModel
import com.example.vkr_rzaevaab.databinding.FragmentDevicePageBinding
import com.example.vkr_rzaevaab.entities.Device
import com.example.vkr_rzaevaab.entities.DeviceReservation
import com.example.vkr_rzaevaab.entities.EventRegistration
import com.example.vkr_rzaevaab.sharedpreferences.SharedPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DevicePageFragment : Fragment() {

    val bundle = Bundle()
    private var _binding: FragmentDevicePageBinding? = null
    private val binding: FragmentDevicePageBinding get() = _binding!!

    private val deviceViewModel: DeviceViewModel by viewModels()
    private val deviceReservViewModel: DeviceReservViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentDevicePageBinding.inflate(inflater, container, false)

        val user_id = SharedPref(requireContext()).getPreferenceString("id")!!.toInt()


        val item = arguments?.getParcelable<Device>("device")
        val device_id = item!!.id
        binding.deviceName.text = item!!.name.toString()
        binding.cpu.text = item!!.cpu.toString()
        binding.ram.text = item!!.ram.toString()
        binding.monitor.text = item!!.monitor.toString()
        binding.videocard.text = item!!.videocard.toString()

        binding.deviceReservButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                bundle.putParcelable("device", item)
//                bundle.putString("device", device_id.toString())
                Navigation.findNavController(it).navigate(R.id.action_devicePageFragment_to_addDeviceReservFragment, bundle)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}