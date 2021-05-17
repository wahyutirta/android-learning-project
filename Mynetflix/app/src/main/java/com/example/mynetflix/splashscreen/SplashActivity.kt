package com.example.mynetflix.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ActivitySplashBinding
import com.example.mynetflix.ui.main.HomeActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var splashActivityBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashActivityBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashActivityBinding.root)

        supportActionBar?.hide()

        Glide.with(this)
            .load(R.drawable.ic_splash_screen)
            .into(splashActivityBinding.imageSplash)

        Handler(Looper.getMainLooper()).postDelayed({
            val myIntent = Intent(this, HomeActivity::class.java)
            startActivity(myIntent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            finish()
        }, 3000)
    }

}