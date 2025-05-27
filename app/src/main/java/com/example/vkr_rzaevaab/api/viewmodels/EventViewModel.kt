package com.example.vkr_rzaevaab.api.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vkr_rzaevaab.DeviceAdapter
import com.example.vkr_rzaevaab.api.Api
import com.example.vkr_rzaevaab.api.adapters.EventAdapter
import com.example.vkr_rzaevaab.app.App
import com.example.vkr_rzaevaab.entities.Device
import com.example.vkr_rzaevaab.entities.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventViewModel : ViewModel() {

    val eventList = MutableLiveData<List<Event>>()
    val event = MutableLiveData<Event>()
    val projectApi = App.retrofit.create(Api::class.java)

    fun createEvent(event: Event) {
        CoroutineScope(Dispatchers.IO).launch {
            projectApi.createEvent(event)
            withContext(Dispatchers.Main){
                eventList.value = projectApi.getAllEvents()
            }
        }
    }

    fun editEvent(id: Int, event: Event) {
        CoroutineScope(Dispatchers.IO).launch {
            projectApi.editEvent(id, event)
            withContext(Dispatchers.Main){
                eventList.value = projectApi.getAllEvents()
            }
        }

    }

    fun getAllEvents(){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getAllEvents()
            withContext(Dispatchers.Main){
                eventList.value = temp
            }
        }
    }

    fun getEventById(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getEventById(id)
            withContext(Dispatchers.Main){
                event.value = temp
            }
        }
    }

    fun createAdapter(events: List<Event>) : EventAdapter {
        return EventAdapter(events)
    }

}