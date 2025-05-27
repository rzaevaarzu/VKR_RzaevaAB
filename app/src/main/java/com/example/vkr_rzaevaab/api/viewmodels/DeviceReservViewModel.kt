package com.example.vkr_rzaevaab.api.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vkr_rzaevaab.api.Api
import com.example.vkr_rzaevaab.app.App
import com.example.vkr_rzaevaab.entities.DeviceReservation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeviceReservViewModel : ViewModel() {

    val deviceReservList = MutableLiveData<List<DeviceReservation>>()
    val deviceReserv = MutableLiveData<DeviceReservation>()
    val projectApi = App.retrofit.create(Api::class.java)


    fun createDeviceReserv(deviceReservation: DeviceReservation) {
        CoroutineScope(Dispatchers.IO).launch {
            projectApi.createDeviceReserv(deviceReservation)
            withContext(Dispatchers.Main){
                deviceReservList.value = projectApi.getAllDeviceReservs()
            }
        }

    }

    fun getAllDeviceReservs(){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getAllDeviceReservs()
            withContext(Dispatchers.Main){
                deviceReservList.value = temp
            }
        }
    }

    fun getDeviceReservById(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getDeviceReservById(id)
            withContext(Dispatchers.Main){
                deviceReserv.value = temp
            }
        }
    }

    fun getDeviceReservByUserId(userId: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getDeviceReservByUserId(userId)
            withContext(Dispatchers.Main){
                deviceReservList.value = temp
            }
        }
    }

    fun getDeviceReservByDeviceId(deviceId: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getDeviceReservByDeviceId(deviceId)
            withContext(Dispatchers.Main){
                deviceReservList.value = temp
            }
        }
    }

    fun getDeviceReservByDeviceIdAndDate(deviceId: Int, date: String){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getDeviceReservByDeviceIdAndDate(deviceId, date)
            withContext(Dispatchers.Main){
                deviceReservList.value = temp
            }
        }
    }

}