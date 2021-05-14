package com.example.mygithubproject.view.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubproject.R
import com.example.mygithubproject.databinding.ActivityUserBinding
import com.example.mygithubproject.services.data.UsersData
import com.example.mygithubproject.sharedpreference.SharedPrefNightMD
import com.example.mygithubproject.view.userdetail.UserDetailActivity
import com.example.mygithubproject.viewmodel.UserAdapter
import com.example.mygithubproject.viewmodel.ViewModelUserMain
import java.util.ArrayList

class UserActivity : AppCompatActivity() {

    private lateinit var sharedpref: SharedPrefNightMD
    private lateinit var adapter: UserAdapter
    private lateinit var VMUserMain: ViewModelUserMain
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        VMUserMain = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ViewModelUserMain::class.java)

        adapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UsersData) {
                Intent(this@UserActivity, UserDetailActivity::class.java).also {
                    it.putExtra(UserDetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(UserDetailActivity.EXTRA_ID, data.id)
                    it.putExtra(UserDetailActivity.EXTRA_URL, data.avatar_url)
                    startActivity(it)
                }
            }
        })

        binding.rvUser.layoutManager = LinearLayoutManager(this@UserActivity)
        binding.rvUser.setHasFixedSize(true)
        binding.rvUser.adapter = adapter
        isNightMode()

        VMUserMain.status.observe(this, {
            it?.let(fun(_: Boolean) {
                when (it) {
                    false -> {
                        onDataError()
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.onstartimage.visibility = View.INVISIBLE
                        binding.rvUser.visibility = View.INVISIBLE
                        binding.onConnectionerror.visibility = View.VISIBLE
                    }
                }
            })
        })

        VMUserMain.getSearchQuery().observe(this, fun(it: ArrayList<UsersData>) {
            showLoading(false)
            adapter.setList(it)
            binding.rvUser.visibility = View.VISIBLE
            binding.onstartimage.visibility = View.INVISIBLE
            binding.onConnectionerror.visibility = View.INVISIBLE
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun isNightMode() {
        sharedpref = SharedPrefNightMD(this)
        if (sharedpref.loadNightMD()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showLoading(true)
                VMUserMain.setSearchQuery(query)

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.move_set -> {
                val intent = Intent(this@UserActivity, SettingsActivity::class.java)
                startActivity(intent)
                overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
                true
            }
            R.id.favorite_menu -> {
                val intent = Intent(this@UserActivity, FavoriteActivity::class.java)
                startActivity(intent)
                overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
                true
            }
            else -> return true
        }
    }

    private fun onDataError() {
        val builder = AlertDialog.Builder(this@UserActivity)
        builder.setTitle(resources.getString(R.string.server_no_resp_message_title))
        builder.setMessage(resources.getString(R.string.no_data_alert_message_body))
        builder.setNegativeButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

}