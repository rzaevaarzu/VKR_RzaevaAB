package com.example.vkr_rzaevaab.api.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vkr_rzaevaab.DeviceAdapter
import com.example.vkr_rzaevaab.api.Api
import com.example.vkr_rzaevaab.app.App
import com.example.vkr_rzaevaab.entities.Device
import com.example.vkr_rzaevaab.entities.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeviceViewModel : ViewModel() {

    val deviceList = MutableLiveData<List<Device>>()
    val device = MutableLiveData<Device>()
    val projectApi = App.retrofit.create(Api::class.java)

    fun createDevice(device: Device) {
        CoroutineScope(Dispatchers.IO).launch {
            projectApi.createDevice(device)
            withContext(Dispatchers.Main){
                deviceList.value = projectApi.getAllDevices()
            }
        }

    }

    fun editDevice(id: Int, device: Device) {
        CoroutineScope(Dispatchers.IO).launch {
            projectApi.editDevice(id, device)
            withContext(Dispatchers.Main){
                deviceList.value = projectApi.getAllDevices()
            }
        }

    }

    fun getAllDevices(){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getAllDevices()
            withContext(Dispatchers.Main){
                deviceList.value = temp
            }
        }
    }

    fun getDeviceById(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getDeviceById(id)
            withContext(Dispatchers.Main){
                device.value = temp
            }
        }
    }

    fun createAdapter(devices: List<Device>) : DeviceAdapter{
        return DeviceAdapter(devices)
    }

}