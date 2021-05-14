package com.example.mygithubproject.view.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.mygithubproject.R
import com.example.mygithubproject.broadcast.AlarmReceiver
import com.example.mygithubproject.databinding.ActivitySettingsBinding
import com.example.mygithubproject.services.dataclass.ReminderNotify
import com.example.mygithubproject.sharedpreference.NotifyReminderPref
import com.example.mygithubproject.sharedpreference.SharedPrefNightMD

class SettingsActivity : AppCompatActivity() {

    private lateinit var reminder: ReminderNotify
    private lateinit var alarmRecvr: AlarmReceiver
    private lateinit var sharedPref: SharedPrefNightMD
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val remaindered = NotifyReminderPref(this)
        binding.swReminder.isChecked = remaindered.getReminder().checkReminder

        alarmRecvr = AlarmReceiver()

        binding.swReminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveReminder(true)
                alarmRecvr.setRepeating(this, "RepeatingAlarm", "09:00", "Github Reminder")
            } else {
                saveReminder(false)
                alarmRecvr.cancel(this)
            }
        }

        supportActionBar?.title = resources.getString(R.string.app_settings)

        sharedPref = SharedPrefNightMD(this)
        if (sharedPref.loadNightMD()) {
            binding.darkMd.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        binding.darkMd.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sharedPref.setNightMD(true)
            } else {
                sharedPref.setNightMD(false)
            }
            this.recreate()
        }
        binding.btnLanguage.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
    }

    private fun saveReminder(state: Boolean) {
        val reminderPref = NotifyReminderPref(this)
        reminder = ReminderNotify()

        reminder.checkReminder = state
        reminderPref.setReminder(reminder)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}