@file:Suppress("DEPRECATION")

package com.example.mygithubuserfavorite.ui.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuserfavorite.R
import com.example.mygithubuserfavorite.data.model.ModelSearchUserItem
import com.example.mygithubuserfavorite.databinding.ActivityMainBinding
import com.example.mygithubuserfavorite.network.APIHelper
import com.example.mygithubuserfavorite.network.NetworkAPIClient
import com.example.mygithubuserfavorite.ui.base.ViewModelFactory
import com.example.mygithubuserfavorite.ui.view.favourite.FavouriteUserActivity
import com.example.mygithubuserfavorite.ui.view.setting.SettingActivity
import com.example.mygithubuserfavorite.ui.viewAdapter.MainItemAdapter
import com.example.mygithubuserfavorite.ui.viewModel.MainViewModel
import com.example.mygithubuserfavorite.utils.Status



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapterMain: MainItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            setting.setOnClickListener(clickListener)
            favourite.setOnClickListener(clickListener)
            search.queryHint = getString(R.string.hint)
            binding.search.onActionViewExpanded()
            showProgress(1)
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    setupViewModel()
                    setUI()
                    setUpObserver(query)
                    return false
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
            search.clearFocus()
        }
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.setting -> {
                val goToSetting = Intent(this, SettingActivity::class.java)
                startActivity(goToSetting)
            }
            R.id.favourite -> {
                val goToFav = Intent(this, FavouriteUserActivity::class.java)
                startActivity(goToFav)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(APIHelper(NetworkAPIClient.apiService)))
            .get(MainViewModel::class.java)
    }

    private fun setUI() {
        binding.apply {
            homeRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapterMain = MainItemAdapter(arrayListOf())
            homeRecyclerView.adapter = adapterMain
        }
    }

    private fun setUpObserver(query: String) {
        viewModel.getItemList(query).observe(this, {
            it?.let { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        showProgress(2)
                        resources.data?.let { userItemList -> retrieve(userItemList.itemUserModels) }
                    }
                    Status.ERROR -> {
                        showProgress(4)
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        showProgress(3)

                    }
                }
            }

        })
    }

    private fun retrieve(users: List<ModelSearchUserItem>) {
        adapterMain.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

    private fun showProgress(status: Int) {
        when (status) {
            1 -> { // On query not filled
                binding.apply {
                    homeRecyclerView.visibility = View.GONE
                    progressAnim.visibility = View.GONE
                    welcome.visibility = View.VISIBLE
                    networkError.visibility = View.GONE
                }
            }
            2 -> { //on success
                binding.apply {
                    homeRecyclerView.visibility = View.VISIBLE
                    progressAnim.visibility = View.GONE
                    welcome.visibility = View.GONE
                    networkError.visibility = View.GONE
                }
            }
            3 -> { // On loadding
                binding.apply {
                    homeRecyclerView.visibility = View.GONE
                    progressAnim.visibility = View.VISIBLE
                    welcome.visibility = View.GONE
                    networkError.visibility = View.GONE
                }
            }
            4 -> { // Network Error
                binding.apply {
                    homeRecyclerView.visibility = View.GONE
                    progressAnim.visibility = View.GONE
                    welcome.visibility = View.GONE
                    networkError.visibility = View.VISIBLE
                }
            }
        }
    }
}