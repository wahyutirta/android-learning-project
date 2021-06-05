package com.example.consumerapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumerapp.R
import com.example.consumerapp.databinding.ActivityMainBinding
import com.example.consumerapp.model.UsersData
import com.example.consumerapp.viewmodel.FavoriteViewModel
import com.example.consumerapp.viewmodel.UserAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var vModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.users_favorite)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        vModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        binding.apply {
            rvFavoriteUsers.setHasFixedSize(true)
            rvFavoriteUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvFavoriteUsers.adapter = adapter
        }
        adapterCallback(adapter)

        vModel.setFavorite(this)

        favoriteObserver(vModel)

    }

    private fun adapterCallback(adapter: UserAdapter){
        adapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UsersData) {
                Toast.makeText(this@MainActivity, "${data.login} tapped", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun favoriteObserver(vModel: FavoriteViewModel){
        vModel.getFavUser().observe(this, {
            when {
                it != null -> {
                    adapter.setList(it)
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}