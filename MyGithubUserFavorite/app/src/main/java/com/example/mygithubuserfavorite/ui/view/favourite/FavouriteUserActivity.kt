package com.example.mygithubuserfavorite.ui.view.favourite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuserfavorite.R
import com.example.mygithubuserfavorite.databinding.ActivityFavouriteUserBinding
import com.example.mygithubuserfavorite.favoriteUserDatabase.db.FavouriteUserHelper
import com.example.mygithubuserfavorite.favoriteUserDatabase.entity.FavouriteUser
import com.example.mygithubuserfavorite.favoriteUserDatabase.helper.MappingHelper
import com.example.mygithubuserfavorite.ui.viewAdapter.FavoriteUserAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavouriteUserActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavouriteUserBinding
    private lateinit var adapter: FavoriteUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            title = getString(R.string.favourite)
            setDisplayHomeAsUpEnabled(true)
            setBackgroundDrawable(getDrawable(R.drawable.base_action_bar_background))
        }
        loadUserAsync()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                super.onBackPressed()
                return true
            }
        }
        return false
    }
    private fun loadUserAsync(){
        GlobalScope.launch(Dispatchers.Main){
            binding.favProgress.visibility = View.VISIBLE
            val favouriteUserHelper = FavouriteUserHelper.getInstance(applicationContext)
            favouriteUserHelper.open()
            val deferredUser = async(Dispatchers.IO){
                val cursor = favouriteUserHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            binding.favProgress.visibility = View.GONE
            val users = deferredUser.await()
            favouriteUserHelper.close()
            if (users.size>0){
                showItem(users)
            }else{
                showSnackbar("Tidak ada data")
            }
        }
    }
    private fun showItem(list : ArrayList<FavouriteUser>){
        binding.rvFavourite.layoutManager = LinearLayoutManager(this)
        adapter = FavoriteUserAdapter(list)
        binding.rvFavourite.adapter = adapter
    }
    private fun showSnackbar(message: String){
        Snackbar.make(binding.rvFavourite,message,Snackbar.LENGTH_SHORT).show()
    }
}