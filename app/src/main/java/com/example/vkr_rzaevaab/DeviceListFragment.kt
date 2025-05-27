package com.example.vkr_rzaevaab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr_rzaevaab.api.viewmodels.DeviceViewModel
import com.example.vkr_rzaevaab.databinding.FragmentDeviceListBinding
import com.example.vkr_rzaevaab.entities.Device


class DeviceListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DeviceAdapter


    private var _binding: FragmentDeviceListBinding? = null
    private val binding: FragmentDeviceListBinding get() = _binding!!

    private val deviceViewModel: DeviceViewModel by viewModels()
    private var gList = listOf<Device>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDeviceListBinding.inflate(inflater, container, false)

        deviceViewModel.getAllDevices()

        binding.deviceList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.deviceList.addItemDecoration(SpacingItemDecoration(4))
        val navController = findNavController()
        deviceViewModel.deviceList.observe(this as LifecycleOwner){ it ->
            adapter = deviceViewModel.createAdapter(it)
            gList = it
            binding.deviceList.adapter = adapter
            adapter!!.onItemClick = {
                val bundle = Bundle()
                bundle.putParcelable("device", it)
                navController.navigate(
                    R.id.action_deviceListFragment_to_devicePageFragment,
                    bundle
                )
            }
        }

        return binding.root
    }
}