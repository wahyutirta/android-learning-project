package com.example.githubsubmission1.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.githubsubmission1.R
import com.example.githubsubmission1.model.UserModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.StringBuilder

class DetailUser : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var UserParcel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        UserParcel = intent.getParcelableExtra<UserModel>(EXTRA_DETAIL) as UserModel

        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = UserParcel.name

        findViewById<FloatingActionButton>(R.id.share).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val shareBody = "Rekomendasi Top Github User -> https://github.com/${UserParcel.username}, Username ${UserParcel.name}, Goodluck!."
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, UserParcel.name)
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(intent, "Selected APPS"))
        }

        findViewById<ImageView>(R.id.avatar).setImageResource(UserParcel.avatar)
        findViewById<TextView>(R.id.username).text = UserParcel.username
        findViewById<TextView>(R.id.name).text = UserParcel.name
        findViewById<TextView>(R.id.follower).text = StringBuilder(resources.getString(R.string.Follower)).append(" ${UserParcel.follower}")
        findViewById<TextView>(R.id.following).text = StringBuilder(resources.getString(R.string.Following)).append(" ${UserParcel.following}")
        findViewById<TextView>(R.id.company).text = StringBuilder(resources.getString(R.string.Company)).append(" ${UserParcel.company}")
        findViewById<TextView>(R.id.location).text = StringBuilder(resources.getString(R.string.Location)).append(" ${UserParcel.location}")
        findViewById<TextView>(R.id.repository).text = StringBuilder(resources.getString(R.string.Repository)).append(" ${UserParcel.repository}")
        findViewById<TextView>(R.id.profil).text = StringBuilder(resources.getString(R.string.Profil)).append(" ${UserParcel.name}")

        findViewById<TextView>(R.id.showprofile).setOnClickListener {
            val url = "https://github.com/${UserParcel.username}"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}