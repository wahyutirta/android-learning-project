package com.example.mygithubproject.sharedpreference

import android.content.Context
import android.content.SharedPreferences

class SharedPrefNightMD (context: Context) {

    private var mySharedPref: SharedPreferences =
        context.getSharedPreferences("filename", Context.MODE_PRIVATE)

    fun setNightMD(state: Boolean) {
        val editor = mySharedPref.edit()
        editor.putBoolean("NightMode", state)
        editor.apply()
    }

    fun loadNightMD(): Boolean {
        return mySharedPref.getBoolean("NightMode", false)
    }

}