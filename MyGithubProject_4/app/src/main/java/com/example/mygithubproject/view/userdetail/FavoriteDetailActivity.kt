package com.example.mygithubproject.view.userdetail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.mygithubproject.R
import com.example.mygithubproject.databinding.ActivityFavoriteDetailBinding
import com.example.mygithubproject.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"

    }
    private var isFavorite: Boolean = false
    private lateinit var fViewModel: DetailViewModel
    private lateinit var binding: ActivityFavoriteDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userNameExtra = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, userNameExtra)

        fViewModel = ViewModelProvider(
            this
        ).get(DetailViewModel::class.java)


        when {
            null != userNameExtra -> {
                onLoading(true)
                fViewModel.loadDetail(userNameExtra)
            }
        }

        statusObserver()
        detailObserver()

        val tabs = findViewById<TabLayout>(R.id.tabs)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)

        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
            tabs.isHorizontalScrollBarEnabled = true
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

    private fun favoriteHandler(isFavorite: Boolean, id: Int): Boolean {
        var favorite: Boolean = isFavorite
        CoroutineScope(Dispatchers.IO).launch {
            val count = fViewModel.checkUser(id)
            withContext(Dispatchers.Main) {
                if (null != count) {
                    if (count > 0) {
                        binding.toggleFavorite.isChecked = true
                        favorite = true
                    } else {
                        binding.toggleFavorite.isChecked = false
                        favorite = false
                    }
                }
            }
        }
        return favorite
    }

    private fun detailObserver() {
        fViewModel.takeDetail().observe(this, {
            when {
                null != it -> {
                    binding.apply {
                        profileLink.text = it.html_url
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

                        Glide.with(this@FavoriteDetailActivity)
                            .load(it.avatar_url)
                            .placeholder(R.drawable.ic_default)
                            .error(R.drawable.ic_connection_error)
                            .into(avatar)
                        onLoading(false)

                        val name = it.name!!
                        val id = it.id!!
                        val avatar = it.avatar_url!!
                        val url = it.html_url!!
                        isFavorite = favoriteHandler(isFavorite, it.id)
                        binding.toggleFavorite.setOnClickListener {
                            isFavorite = !isFavorite
                            if (isFavorite) {
                                fViewModel.addToFav(name, id, avatar, url)
                            } else {
                                fViewModel.userRemoveFromFav(it.id)
                            }
                            binding.toggleFavorite.isChecked = isFavorite
                        }
                    }
                }
            }
        })
    }

    private fun statusObserver() {
        fViewModel.status.observe(this, fun(status: Boolean?) {
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
    }

    private fun onLoading(state: Boolean) {
        when (state) {
            true -> {
                binding.progressDetail.visibility = View.VISIBLE
            }
            else -> {
                binding.progressDetail.visibility = View.GONE
            }
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