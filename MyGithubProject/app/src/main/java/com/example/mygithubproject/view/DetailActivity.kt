package com.example.mygithubproject.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mygithubproject.R
import com.example.mygithubproject.model.UserModel
import com.example.mygithubproject.viewmodel.PagerSectionAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (supportActionBar != null) {
            supportActionBar?.title = resources.getString(R.string.detail_user)
        }
        viewPagerConfig()
        loadUser()
    }
    private fun viewPagerConfig() {
        val viewPagerDetail = PagerSectionAdapter(this, supportFragmentManager)
        view_pager.adapter = viewPagerDetail
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }
    @SuppressLint("SetTextI18n")
    private fun loadUser() {
        val dataUser = intent.getParcelableExtra<UserModel>(EXTRA_DETAIL) as UserModel
        Glide.with(this)
            .load(dataUser.avatar)
            .apply(RequestOptions().override(150, 150))
            .into(detail_avatar)
        detail_name.text = dataUser.name
        detail_username.text = "Github.com/${dataUser.username}"
        detail_company.text = "${getString(R.string.company)} ${dataUser.company}"
        detail_location.text = "${getString(R.string.location)} ${dataUser.location}"
        detail_following.text = "${getString(R.string.following)} ${dataUser.following}"
        detail_follower.text = "${getString(R.string.follower)} ${dataUser.followers}"
        detail_user_repository.text = "${getString(R.string.repository)} ${dataUser.repository}"
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val moveIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(moveIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}