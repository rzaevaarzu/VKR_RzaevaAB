package com.example.vkr_rzaevaab.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.vkr_rzaevaab.entities.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPref (context: Context) {
    private val preference_Name = "Arzuzer"
    val shared_Pref: SharedPreferences = context.getSharedPreferences(preference_Name, Context.MODE_PRIVATE)
    var sharedUser: User? = null
    private val gson = Gson()

    /*Stored String Data*/
    fun saveString(key_name: String, text: String) {
        val editor: SharedPreferences.Editor = shared_Pref.edit()
        editor.putString(key_name, text)
        editor.apply()
    }
    fun saveLong(key_name: String, text: Long) {
        val editor: SharedPreferences.Editor = shared_Pref.edit()
        editor.putLong(key_name, text)
        editor.apply()
    }
    fun saveBool(key_name: String, text: Boolean) {
        val editor: SharedPreferences.Editor = shared_Pref.edit()
        editor.putBoolean(key_name, text)
        editor.apply()
    }

    fun getString(key_name: String): String? {
        return shared_Pref.getString(key_name, null)
    }
    fun getLong(key_name: String): Long {
        return shared_Pref.getLong(key_name, 0)
    }
    fun getBool(key_name: String): Boolean {
        return shared_Pref.getBoolean(key_name,false)
    }
    fun getList(key: String): List<Any> {
        val json = shared_Pref.getString(key, null)
        val type = object : TypeToken<List<Any>>() {}.type
        return gson.fromJson(json, type) ?: listOf()
    }
    fun saveList(key: String, list: List<Any>) {
        val editor = shared_Pref.edit()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }


    //функция для сохранения данных в объект
    fun saveUser(id: String, fio: String, birthDate: String, telephone: String, email: String, isAdmin: Boolean, key_name: String, text: String) {
        val editor: SharedPreferences.Editor = shared_Pref.edit()
        editor.putString(key_name, text)
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_USER_NAME, fio)
        editor.putString(KEY_BIRTHDATE, birthDate)
        editor.putString(KEY_TELEPHONE, telephone)
        editor.putString(KEY_EMAIL, email)
        editor.putBoolean(IS_ADMIN, isAdmin)
        editor.putString(KEY_USER_ID, id)
        editor.apply()
    }

    //объект хранящий данные залогиненного пользователя
    companion object{
        val IS_LOGIN = "isLoggedIn"
        val KEY_USER_NAME = "user_fio"
        val KEY_BIRTHDATE = "birth_date"
        val KEY_TELEPHONE = "telephone"
        val KEY_EMAIL = "email"
        val IS_ADMIN = "is_admin"
        val KEY_USER_ID = "id"
    }


    //возвращает данные о залогиненном пользователе в виде словаря ключ-значение
    fun getPreferenceString(key_name: String): String? {
        return shared_Pref.getString(key_name, null)
    }



    //очищает строку состояния, очищает объект
    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = shared_Pref.edit()
        editor.clear()
        editor.apply()
    }


}