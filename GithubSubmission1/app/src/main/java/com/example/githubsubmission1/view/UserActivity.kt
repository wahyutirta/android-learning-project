package com.example.githubsubmission1.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsubmission1.R
import com.example.githubsubmission1.adapter.UserAdapter
import com.example.githubsubmission1.helper.UserModelHelper
import com.example.githubsubmission1.model.UserModel
import com.example.githubsubmission1.sharedpref.SPNightMode
import java.util.*
import kotlin.collections.ArrayList

class UserActivity : AppCompatActivity() {
    private lateinit var sharedpref: SPNightMode
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val rvRecyclerView = findViewById<RecyclerView>(R.id.rv_user)

        rvRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = UserAdapter(UserModelHelper.getVersionsList())
        rvRecyclerView.adapter = adapter
        isNightMode()

        //callback
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserModel) {
                showSelectedUser(data)
            }
        })

    }

    private fun showSelectedUser(user: UserModel) {
        Toast.makeText(this, "Kamu memilih " + user.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.mode_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.cari)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@UserActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }
            override fun onQueryTextChange(text: String): Boolean {
                filter(text)
                return true
            }
        })
        return true
    }

    fun filter(text: String) {

        val filteredDataAry: ArrayList<UserModel> = ArrayList()

        val userArray : ArrayList<UserModel> = UserModelHelper.getVersionsList()

        for (eachData in userArray) {
            if (eachData.name!!.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)) || eachData.username!!.toLowerCase(
                    Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredDataAry.add(eachData)
            }
        }
        adapter.filterList(filteredDataAry)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dark_mode -> {
                sharedpref.setNightModeState(true)
                refreshDarkMode()
                true
            }
            R.id.sun_mode -> {
                sharedpref.setNightModeState(false)
                refreshDarkMode()
                true
            }
            else -> true
        }
    }
    private fun isNightMode() {
        sharedpref = SPNightMode(this)
        if (sharedpref.loadNightModeState()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
    private fun refreshDarkMode() {
        val myIntent = Intent(this, UserActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        finish()
    }
}