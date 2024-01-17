package com.example.myped

import android.content.Context
import android.content.SharedPreferences

class Helper(context: Context) {

    private val sharedPrefFile = "userPreference"
    private var sharedPref: SharedPreferences =
        context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    fun putObStatus(key: String, value: String) {
        editor.putString(key, value).apply()
    }
    fun getObStatus(key: String): String? {
        return sharedPref.getString(key, null)
    }

}