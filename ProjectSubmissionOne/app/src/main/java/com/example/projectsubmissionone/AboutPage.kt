package com.example.projectsubmissionone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AboutPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_page)
        val actionbar = supportActionBar
        actionbar!!.title= "About Me"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}