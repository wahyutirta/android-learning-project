package com.example.mygithubproject.sharedpreference

import android.content.Context
import com.example.mygithubproject.services.dataclass.ReminderNotify

class NotifyReminderPref(context: Context) {
    private val pref = context.getSharedPreferences(PREFS_NOTIFY_NAME, Context.MODE_PRIVATE)

    companion object {
        const val PREFS_NOTIFY_NAME = "reminder_pref"
        private const val REMINDER = "isRemind"
    }

    fun putReminder(value: ReminderNotify) {

        val prefEditor = pref.edit()
        prefEditor.putBoolean(REMINDER, value.checkReminder)
        prefEditor.apply()
    }

    fun letReminder(): ReminderNotify {
        val mReminder = ReminderNotify()
        mReminder.checkReminder = pref.getBoolean(REMINDER, false)
        return mReminder
    }

}