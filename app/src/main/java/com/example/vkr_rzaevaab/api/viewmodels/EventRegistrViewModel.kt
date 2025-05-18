package com.example.vkr_rzaevaab.api.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vkr_rzaevaab.api.Api
import com.example.vkr_rzaevaab.app.App
import com.example.vkr_rzaevaab.entities.DeviceReservation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventRegistrViewModel : ViewModel() {

    val eventRegList = MutableLiveData<List<DeviceReservation>>()
    val eventReg = MutableLiveData<DeviceReservation>()
    val count = MutableLiveData<Int>()
    val projectApi = App.retrofit.create(Api::class.java)

    fun createEventReg(eventRegistration: DeviceReservation) {
        CoroutineScope(Dispatchers.IO).launch {
            projectApi.createEventRegistr(eventRegistration)
            withContext(Dispatchers.Main){
                eventRegList.value = projectApi.getAllEventRegistrs()
            }
        }

    }

    fun getAllEventRegs(){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getAllEventRegistrs()
            withContext(Dispatchers.Main){
                eventRegList.value = temp
            }
        }
    }

    fun getEventRegById(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getEventRegitrById(id)
            withContext(Dispatchers.Main){
                eventReg.value = temp
            }
        }
    }

    fun getEventRegByUserId(userId: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getEventRegistrByUserId(userId)
            withContext(Dispatchers.Main){
                eventRegList.value = temp
            }
        }
    }

    fun getEventRegByEventId(eventId: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getEventRegistrByEventId(eventId)
            withContext(Dispatchers.Main){
                eventRegList.value = temp
            }
        }
    }

    fun getCountOfRemainingSeats(eventId: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getCountOfRemainingSeats(eventId)
            withContext(Dispatchers.Main){
                // Должно быть количество (Integer)
                count.value = temp
            }
        }
    }

}