package com.example.mygithubproject.sharedpreference

import android.content.Context
import com.example.mygithubproject.modelandservice.dataclass.ReminderNotify

class NotifyReminderPreference (context: Context) {
    companion object {
        const val PREFS_NOTIFY_NAME = "reminder_pref"
        private const val REMINDER = "isRemind"
    }

    private val preference = context.getSharedPreferences(PREFS_NOTIFY_NAME, Context.MODE_PRIVATE)

    fun setReminder(value: ReminderNotify) {

        val editor = preference.edit()
        editor.putBoolean(REMINDER, value.isReminder)
        editor.apply()

    }

    fun getReminder(): ReminderNotify {
        val model = ReminderNotify()
        model.isReminder = preference.getBoolean(REMINDER, false)
        return model
    }

}