package com.example.mynetflix.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        supportActionBar?.hide()

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.hometabs.setupWithViewPager(activityHomeBinding.viewPager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter

        supportActionBar?.elevation = 0f
    }
}