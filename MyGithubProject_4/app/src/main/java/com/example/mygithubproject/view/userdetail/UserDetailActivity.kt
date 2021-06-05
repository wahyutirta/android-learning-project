package com.example.mygithubproject.view.userdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.mygithubproject.R
import com.example.mygithubproject.databinding.ActivityUserDetailBinding
import com.example.mygithubproject.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityUserDetailBinding
    private var isFavorite: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(
            this
        ).get(DetailViewModel::class.java)

        if (null != username) {
            onLoading(true)
            viewModel.loadDetail(username)
        }
        statusObserve(viewModel)
        detailObserve(viewModel, binding)

        val tabs = findViewById<TabLayout>(R.id.tabs)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
            tabs.isHorizontalScrollBarEnabled = true
        }
        listener(binding)

    }

    private fun listener(binding: ActivityUserDetailBinding) {
        binding.showprofile.setOnClickListener {
            val url = binding.profileLink.text.toString()
            val message = StringBuilder("Open ").append(" ", url)
            Toast.makeText(this@UserDetailActivity, message, Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        binding.fabShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            Toast.makeText(this@UserDetailActivity, "share user", Toast.LENGTH_SHORT).show()
            val shareBody =
                "${resources.getString(R.string.share_builder1)} ${binding.username.text}${
                    resources.getString(
                        R.string.share_builder2
                    )
                } ${binding.profileLink.text}"
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            intent.putExtra(Intent.EXTRA_SUBJECT, findViewById<TextView>(R.id.username).text)
            startActivity(Intent.createChooser(intent, resources.getString(R.string.share_title)))
        }
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

    private fun bindingDetail(binding: ActivityUserDetailBinding, state: Boolean) {

        if (state != true) {
            binding.detailLayout.visibility = View.INVISIBLE
            binding.viewPager.visibility = View.INVISIBLE
            binding.tabs.visibility = View.INVISIBLE
        }


    }

    private fun onError(binding: ActivityUserDetailBinding) {
        bindingDetail(binding, false)
        binding.onErrorData.visibility = View.VISIBLE
        buildAlert()
    }

    private fun buildAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.server_no_resp_message_title))
            .setMessage(resources.getString(R.string.no_data_alert_message_body))
            .setNegativeButton("OK") { dialog, _ -> // Do nothing
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    private fun statusObserve(viewModel: DetailViewModel) {
        viewModel.status.observe(this, fun(status: Boolean?) {
            status?.let {
                when (status) {
                    false -> {
                        onError(binding)
                        onLoading(false)

                    }
                }
            }
        })
    }

    private fun detailObserve(viewModel: DetailViewModel, binding: ActivityUserDetailBinding) {
        viewModel.takeDetail().observe(this, {

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

                        Glide.with(this@UserDetailActivity)
                            .load(it.avatar_url)
                            .placeholder(R.drawable.ic_default)
                            .error(R.drawable.ic_connection_error)
                            .into(avatar)

                        val id = it.id
                        val login = if (it.login != null) it.login.toString() else "null"
                        val avatar = if (it.avatar_url != null) it.avatar_url.toString() else "null"
                        val html = if (it.html_url != null) it.html_url.toString() else "null"

                        favoriteHandler(login, id, binding)
                        binding.toggleFavorite.setOnClickListener {

                            isFavorite = binding.toggleFavorite.isChecked
                            if (isFavorite) {
                                viewModel.addUser(login, id, avatar, html)
                                val message = StringBuilder(login).append(
                                    " ",
                                    resources.getString(R.string.add)
                                )
                                Toast.makeText(this@UserDetailActivity, message, Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                viewModel.removeUser(login, id)
                                val message = StringBuilder(login).append(
                                    " ",
                                    resources.getString(R.string.delete)
                                )
                                Toast.makeText(this@UserDetailActivity, message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            binding.toggleFavorite.isChecked = isFavorite
                        }
                        onLoading(false)
                    }
                }
            }
        })
    }

    private fun favoriteHandler(login: String, id: Int, binding: ActivityUserDetailBinding) {
        CoroutineScope(Dispatchers.IO).launch {

            val check = viewModel.checkUser(login, id)
            withContext(Dispatchers.Main) {

                if (check != null && check > 0) {
                    binding.toggleFavorite.isChecked = true
                    isFavorite = true
                } else {
                    binding.toggleFavorite.isChecked = false
                    isFavorite = false
                }
                binding.toggleFavorite.isChecked = isFavorite

            }
        }

    }


}