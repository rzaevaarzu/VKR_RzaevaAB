package com.example.vkr_rzaevaab.api.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.vkr_rzaevaab.api.Api
import com.example.vkr_rzaevaab.app.App
import com.example.vkr_rzaevaab.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {

    val userList = MutableLiveData<List<User>>()
    val projectApi = App.retrofit.create(Api::class.java)

    fun createUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            projectApi.createUser(user)
            withContext(Dispatchers.Main){
                userList.value = projectApi.getAllUsers()
            }
        }

    }

    fun getAllUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            val temp = projectApi.getAllUsers()
            withContext(Dispatchers.Main){
                userList.value = temp
            }
        }
    }

}