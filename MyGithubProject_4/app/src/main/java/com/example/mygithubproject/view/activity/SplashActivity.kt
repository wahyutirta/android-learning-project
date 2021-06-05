package com.example.mygithubproject.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mygithubproject.R
import com.example.mygithubproject.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Glide.with(this).load(R.drawable.ic_github_logo).apply(RequestOptions().override(120, 120))
            .into(binding.splashBG)

        Handler(Looper.getMainLooper()).postDelayed({
            val myIntent = Intent(this, HomeActivity::class.java)
            startActivity(myIntent)
            finish()
        }, 2000)
    }
}