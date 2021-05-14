package com.example.mygithubproject.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubproject.R
import com.example.mygithubproject.databinding.ActivityFavoriteBinding
import com.example.mygithubproject.services.data.FavUsers
import com.example.mygithubproject.services.data.UsersData
import com.example.mygithubproject.view.userdetail.UserDetailActivity
import com.example.mygithubproject.viewmodel.UserAdapter
import com.example.mygithubproject.viewmodel.ViewModelUserFavorite

class FavoriteActivity : AppCompatActivity() {

    private lateinit var VMUserFavorite: ViewModelUserFavorite
    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.Fav_user)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        VMUserFavorite = ViewModelProvider(this).get(ViewModelUserFavorite::class.java)

        adapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UsersData) {
                Intent(this@FavoriteActivity, UserDetailActivity::class.java).also {
                    it.putExtra(UserDetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(UserDetailActivity.EXTRA_ID, data.id)
                    it.putExtra(UserDetailActivity.EXTRA_URL, data.avatar_url)
                    startActivity(it)
                }
            }
        })


        binding.apply {
            rvFavUsers.setHasFixedSize(true)
            rvFavUsers.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavUsers.adapter = adapter
        }

        VMUserFavorite.getFavUsers()?.observe(this, {
            when {
                it != null -> {
                    val list = mapList(it)
                    adapter.setList(list)
                }
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun mapList(users: List<FavUsers>): ArrayList<UsersData> {
        val listUsers = ArrayList<UsersData>()
        users.forEach { user ->
            val mapped = UsersData(
                user.login,
                user.id,
                user.avatarUrl

            )
            listUsers.add(mapped)
        }
        return listUsers
    }



}