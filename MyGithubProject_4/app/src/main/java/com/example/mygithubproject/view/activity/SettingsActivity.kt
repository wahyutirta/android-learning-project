package com.example.mygithubproject.view.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.mygithubproject.R
import com.example.mygithubproject.broadcast.AlarmReceiver
import com.example.mygithubproject.databinding.ActivitySettingsBinding
import com.example.mygithubproject.services.dataclass.ReminderNotify
import com.example.mygithubproject.sharedpreference.NotifyReminderPref
import com.example.mygithubproject.sharedpreference.SharedPrefNightMD
import java.lang.StringBuilder

class SettingsActivity : AppCompatActivity() {

    private lateinit var reminder: ReminderNotify
    private lateinit var alarmRecvr: AlarmReceiver
    private lateinit var sharedPref: SharedPrefNightMD
    private lateinit var binding: ActivitySettingsBinding

    private fun keepReminder(state: Boolean) {
        val reminderPref = NotifyReminderPref(this)
        reminder = ReminderNotify()

        reminder.checkReminder = state
        reminderPref.putReminder(reminder)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = resources.getString(R.string.app_settings)

        reminderHandler(binding)

        nightMDHandler(binding)
        binding.btnLanguage.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun nightMDHandler(binding: ActivitySettingsBinding) {
        sharedPref = SharedPrefNightMD(this)

        when (sharedPref.loadNightMD()) {
            true -> {
                binding.darkMd.isChecked = true
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false -> {
                binding.darkMd.isChecked = false
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.darkMd.setOnCheckedChangeListener { _, isChecked ->
            sharedPref.setNightMD(isChecked)
            val message = StringBuilder("Night Mode").append(if (isChecked) " On" else " Off")
            Toast.makeText(this@SettingsActivity, message, Toast.LENGTH_SHORT).show()
            this.recreate()
        }

    }

    private fun reminderHandler(binding: ActivitySettingsBinding) {
        val remaindered = NotifyReminderPref(this)
        binding.swReminder.isChecked = remaindered.letReminder().checkReminder

        alarmRecvr = AlarmReceiver()

        binding.swReminder.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    alarmRecvr.setRepeating(
                        this,
                        "RepeatingAlarm",
                        "09:00",
                        resources.getString(R.string.alarm_message)
                    )
                    keepReminder(isChecked)
                }
                else -> {
                    alarmRecvr.cancel(this)
                    keepReminder(isChecked)
                }
            }
        }
    }


}