package com.example.consumerapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumerapp.R
import com.example.consumerapp.databinding.ActivityMainBinding
import com.example.consumerapp.viewmodel.FavoriteViewModel
import com.example.consumerapp.viewmodel.UserAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.Favorite_user)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        binding.apply {
            rvUserFav.setHasFixedSize(true)
            rvUserFav.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUserFav.adapter = adapter
        }

        viewModel.setFavorite(this)

        viewModel.getFavUser().observe(this, {
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