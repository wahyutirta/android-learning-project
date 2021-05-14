package com.example.mygithubproject.sharedpreference

import android.content.Context
import com.example.mygithubproject.services.dataclass.ReminderNotify

class NotifyReminderPref (context: Context) {
    private val pref = context.getSharedPreferences(PREFS_NOTIFY_NAME, Context.MODE_PRIVATE)
    companion object {
        const val PREFS_NOTIFY_NAME = "reminder_pref"
        private const val REMINDER = "isRemind"
    }
    fun getReminder(): ReminderNotify {
        val model = ReminderNotify()
        model.checkReminder = pref.getBoolean(REMINDER, false)
        return model
    }
    fun setReminder(value: ReminderNotify) {

        val editor = pref.edit()
        editor.putBoolean(REMINDER, value.checkReminder)
        editor.apply()
    }
}