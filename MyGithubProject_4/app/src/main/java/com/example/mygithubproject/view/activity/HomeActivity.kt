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
import com.example.mygithubproject.databinding.ActivityHomeBinding
import com.example.mygithubproject.services.data.UsersData
import com.example.mygithubproject.sharedpreference.SharedPrefNightMD
import com.example.mygithubproject.view.userdetail.UserDetailActivity
import com.example.mygithubproject.viewmodel.UserAdapter
import com.example.mygithubproject.viewmodel.HomeViewModel
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {

    private lateinit var sharedpref: SharedPrefNightMD
    private lateinit var adapter: UserAdapter
    private lateinit var VMHome: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        adapterCallback(adapter)

        binding.rvUsers.layoutManager = LinearLayoutManager(this@HomeActivity)
        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.adapter = adapter

        VMHome = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(HomeViewModel::class.java)
        flagObserver(VMHome)
        searchQueryObserver(VMHome, adapter)
        checkNightMode()
    }

    private fun adapterCallback(adapter: UserAdapter){
        adapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UsersData) {
                Intent(this@HomeActivity, UserDetailActivity::class.java).also {
                    it.putExtra(UserDetailActivity.EXTRA_USERNAME, data.login)

                    startActivity(it)
                }
            }
        })
    }

    private fun flagObserver(viewModel: HomeViewModel){
        viewModel.flag.observe(this, {
            it?.let(fun(_: Boolean) {
                if (it == false) {
                    onError(true)
                }
            })
        })
    }

    private fun searchQueryObserver(viewModel: HomeViewModel, adapter: UserAdapter){
        viewModel.takeSearchQuery().observe(this, fun(it: ArrayList<UsersData>) {
            onLoading(false)
            adapter.setList(it)
            onRV(true)
        })
    }

    private fun onError(state: Boolean){
        when (state) {
            true -> {
                whenDataError()
                binding.progressBar.visibility = View.INVISIBLE
                binding.onstartimage.visibility = View.INVISIBLE
                binding.rvUsers.visibility = View.INVISIBLE
                binding.onConnectionerror.visibility = View.VISIBLE
            }
            else -> {

            }
        }

    }

    private fun onRV(state: Boolean) {
        when (state) {
            true -> {
                binding.onConnectionerror.visibility = View.INVISIBLE
                binding.onstartimage.visibility = View.INVISIBLE
                binding.rvUsers.visibility = View.VISIBLE

            }
            else -> {
                binding.rvUsers.visibility = View.INVISIBLE
                binding.onConnectionerror.visibility = View.VISIBLE
            }
        }
    }

    private fun whenDataError() {
        val builder = AlertDialog.Builder(this@HomeActivity)
        builder.setTitle(resources.getString(R.string.server_no_resp_message_title))
        builder.setMessage(resources.getString(R.string.no_data_alert_message_body))
        builder.setNegativeButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun onLoading(state: Boolean) {
        when {
            state -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            else -> {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun checkNightMode() {
        sharedpref = SharedPrefNightMD(this)

        when(sharedpref.loadNightMD()){
            true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
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
                onLoading(true)
                VMHome.loadSearchQuery(query)
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
                val intent = Intent(this@HomeActivity, SettingsActivity::class.java)
                startActivity(intent)
                overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
                true
            }
            R.id.favorite_menu -> {
                val intent = Intent(this@HomeActivity, FavoriteActivity::class.java)
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


}