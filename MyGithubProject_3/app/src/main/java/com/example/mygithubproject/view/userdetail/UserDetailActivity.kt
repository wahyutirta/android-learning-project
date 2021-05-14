package com.example.mygithubproject.view.userdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.mygithubproject.R
import com.example.mygithubproject.databinding.ActivityUserDetailBinding
import com.example.mygithubproject.viewmodel.ViewModelUserDetail
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"
    }

    private lateinit var VMUserDetail: ViewModelUserDetail
    private lateinit var binding: ActivityUserDetailBinding

    private fun setLoading(state: Boolean) {
        when (state) {
            true -> {
                binding.progressDetail.visibility = View.VISIBLE
            }
            else -> {
                binding.progressDetail.visibility = View.GONE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userNameExtra = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, userNameExtra)

        VMUserDetail = ViewModelProvider(
            this
        ).get(ViewModelUserDetail::class.java)

        when {
            null != userNameExtra -> {
                setLoading(true)
                VMUserDetail.loadDetail(userNameExtra)
            }
        }


        val tabs = findViewById<TabLayout>(R.id.tabs)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        VMUserDetail.status.observe(this, fun(status: Boolean?) {
            status?.let {
                when (status) {
                    false -> {
                        alertError()
                        binding.progressDetail.visibility = View.GONE
                        binding.detailLayout.visibility = View.INVISIBLE
                        binding.onErrorData.visibility = View.VISIBLE
                        binding.tabs.visibility = View.INVISIBLE
                        binding.viewPager.visibility = View.INVISIBLE
                    }
                }
            }
        })

        VMUserDetail.takeDetail().observe(this, {

            when {
                null != it -> {
                    binding.apply {
                        profileLink.text = StringBuilder("https://github.com/").append("${it.login}")
                        username.text = it.name
                        follower.text =
                            StringBuilder(resources.getString(R.string.follower)).append(" ${it.followers}")
                        following.text =
                            StringBuilder(resources.getString(R.string.following)).append(" ${it.following}")
                        when ("${it.company}" == "null") {
                            true -> company.text =
                                StringBuilder(resources.getString(R.string.company)).append(
                                    " ",
                                    StringBuilder(resources.getString(R.string.not_recorded))
                                )
                            false -> company.text =
                                StringBuilder(resources.getString(R.string.company)).append(" ${it.company}")
                        }
                        when ("${it.location}" == "null") {
                            true -> location.text =
                                StringBuilder(resources.getString(R.string.location)).append(
                                    " ",
                                    StringBuilder(resources.getString(R.string.not_recorded))
                                )
                            false -> location.text =
                                StringBuilder(resources.getString(R.string.location)).append(" ${it.location}")
                        }
                        repository.text =
                            StringBuilder(resources.getString(R.string.repo)).append(" ${it.public_repos}")
                        profil.text =
                            StringBuilder(resources.getString(R.string.profile)).append(" ${it.name}")

                        Glide.with(this@UserDetailActivity)
                            .load(it.avatar_url)
                            .placeholder(R.drawable.ic_default)
                            .error(R.drawable.ic_connection_error)
                            .into(avatar)
                        setLoading(false)
                    }
                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
            tabs.isHorizontalScrollBarEnabled = true
        }

        var isFavorite = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = VMUserDetail.checkUser(id)
            withContext(Dispatchers.Main) {
                if (null != count) {
                    if (count > 0) {
                        binding.toggleFavorite.isChecked = true
                        isFavorite = true
                    } else {
                        binding.toggleFavorite.isChecked = false
                        isFavorite = false
                    }
                }
            }
        }

        binding.toggleFavorite.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                userNameExtra?.let { it1 -> VMUserDetail.addToFav(it1, id, avatarUrl!!) }
            } else {
                VMUserDetail.userRemoveFromFav(id)
            }
            binding.toggleFavorite.isChecked = isFavorite
        }

        binding.showprofile.setOnClickListener {
            val url = binding.profileLink.text.toString()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        binding.fabShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val shareBody =
                "${resources.getString(R.string.share_builder1)} ${
                    binding.profileLink.text
                }, ${resources.getString(R.string.share_builder2)} ${binding.username.text}, ${
                    resources.getString(
                        R.string.share_builder3
                    )
                }"
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, findViewById<TextView>(R.id.username).text)
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(intent, resources.getString(R.string.share_title)))
        }
    }


    private fun alertError() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.server_no_resp_message_title))
        builder.setMessage(resources.getString(R.string.no_data_alert_message_body))
        builder.setNegativeButton("OK") { dialog, _ -> // Do nothing
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }


}