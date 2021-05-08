package com.example.mygithubproject.view

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.mygithubproject.R
import com.example.mygithubproject.broadcast.AlarmReceiver
import com.example.mygithubproject.databinding.ActivitySettingsBinding
import com.example.mygithubproject.modelandservice.dataclass.ReminderNotify
import com.example.mygithubproject.sharedpreference.NotifyReminderPreference
import com.example.mygithubproject.sharedpreference.SharedpreferenceNightMD

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedpreferenceNightMD
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var reminder: ReminderNotify
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val remaindered = NotifyReminderPreference(this)
        binding.switchReminder.isChecked = remaindered.getReminder().isReminder

        alarmReceiver = AlarmReceiver()

        binding.switchReminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(this, "RepeatingAlarm", "09:00", "Github Reminder")
            } else {
                saveReminder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }

        supportActionBar?.title = resources.getString(R.string.Setting)

        sharedPref = SharedpreferenceNightMD(this)
        if (sharedPref.loadNightModeState()) {
            binding.darkMode.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        binding.darkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sharedPref.setNightModeState(true)
            } else {
                sharedPref.setNightModeState(false)
            }
            this.recreate()
        }
        binding.btnLanguage.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
    }

    private fun saveReminder(state: Boolean) {
        val reminderPref = NotifyReminderPreference(this)
        reminder = ReminderNotify()

        reminder.isReminder = state
        reminderPref.setReminder(reminder)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}