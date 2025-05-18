package com.example.vkr_rzaevaab.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//главный класс приложения, создаётся сразу после запуска. Создает экземпляр базы данных и репозиториев для работы с ней
class App : Application(){
    companion object{

        lateinit var retrofit: Retrofit

    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.25.253:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        if (getSavedThemeState()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun getSavedThemeState():Boolean {
        val sharPref: SharedPreferences = getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        return sharPref.getBoolean("isDarkTheme", false)
    }
}