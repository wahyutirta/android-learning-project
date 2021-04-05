package com.example.githubsubmission1.sharedpref

import android.content.Context
import android.content.SharedPreferences

class SPNightMode(context: Context) {
    private var mySharedPref: SharedPreferences = context.getSharedPreferences("filename", Context.MODE_PRIVATE)

    fun setNightModeState(state:Boolean) {
        val editor = mySharedPref.edit()
        editor.putBoolean("NightMode", state)
        editor.apply()
    }
    fun loadNightModeState():Boolean {
        return mySharedPref.getBoolean("NightMode", false)
    }
}