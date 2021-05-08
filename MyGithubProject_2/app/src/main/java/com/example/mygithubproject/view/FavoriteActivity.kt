package com.example.mygithubproject.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubproject.R
import com.example.mygithubproject.databinding.ActivityFavoriteBinding
import com.example.mygithubproject.modelandservice.data.FavoriteUser
import com.example.mygithubproject.modelandservice.data.UsersData
import com.example.mygithubproject.view.userdetail.UserDetailActivity
import com.example.mygithubproject.viewmodel.UserAdapter
import com.example.mygithubproject.viewmodel.UserFavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: UserFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.Favorite_user)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(UserFavoriteViewModel::class.java)

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
            rvUserFav.setHasFixedSize(true)
            rvUserFav.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvUserFav.adapter = adapter
        }

        viewModel.getFavUser()?.observe(this, {
            if (null != it) {
                val list = mapList(it)
                adapter.setList(list)
            }
        })

    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<UsersData> {
        val listUsers = ArrayList<UsersData>()
        for (user in users) {
            val userMapped = UsersData(
                user.login,
                user.id,
                user.avatar_url

            )
            listUsers.add(userMapped)
        }
        return listUsers
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}