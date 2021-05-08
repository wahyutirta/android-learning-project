package com.example.mygithubuser.userinterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mygithubuser.R
import com.example.mygithubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}