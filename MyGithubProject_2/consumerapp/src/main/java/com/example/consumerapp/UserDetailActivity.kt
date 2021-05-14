package com.example.consumerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.consumerapp.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"
    }

    private lateinit var VMUserDetail: ViewModelUserDetail
    private lateinit var binding: ActivityUserDetailBinding

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

        if (null != userNameExtra) {
            showLoading(true)
            VMUserDetail.setDetail(userNameExtra)
        }
        val link = "https://github.com/"


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

        VMUserDetail.getDetail().observe(this, {

            if (null != it) {

                binding.apply {
                    name.text = StringBuilder(link).append("${it.login}")
                    username.text = it.name
                    follower.text =
                        StringBuilder(resources.getString(R.string.follower)).append(" ${it.followers}")
                    following.text =
                        StringBuilder(resources.getString(R.string.following)).append(" ${it.following}")
                    when ("${it.company}" == "null"){
                        true -> company.text = StringBuilder(resources.getString(R.string.company)).append(" ",StringBuilder(resources.getString(R.string.not_recorded)))
                        false -> company.text = StringBuilder(resources.getString(R.string.company)).append(" ${it.company}")
                    }
                    when ("${it.location}" == "null"){
                        true -> location.text = StringBuilder(resources.getString(R.string.location)).append(" ",StringBuilder(resources.getString(R.string.not_recorded)))
                        false -> location.text = StringBuilder(resources.getString(R.string.location)).append(" ${it.location}")
                    }
                    repository.text =
                        StringBuilder(resources.getString(R.string.repo)).append(" ${it.repos_url}")
                    profil.text =
                        StringBuilder(resources.getString(R.string.profile)).append(" ${it.name}")

                    Glide.with(this@UserDetailActivity)
                        .load(it.avatar_url)
                        .placeholder(R.drawable.ic_default)
                        .error(R.drawable.ic_connection_error)
                        .into(avatar)
                    showLoading(false)
                }
            }
        })

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

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressDetail.visibility = View.VISIBLE
        } else {
            binding.progressDetail.visibility = View.GONE
        }
    }

}