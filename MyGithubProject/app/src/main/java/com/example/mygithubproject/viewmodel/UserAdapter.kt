package com.example.mygithubproject.viewmodel

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mygithubproject.R
import com.example.mygithubproject.model.UserModel
import com.example.mygithubproject.view.DetailActivity
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(private val userList: ArrayList<UserModel>) :
    RecyclerView.Adapter<UserAdapter.ListUserHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.ListUserHolder {
        return ListUserHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserAdapter.ListUserHolder, position: Int) {
        holder.bind(userList[position])

        val userData = userList[position]
        holder.itemView.setOnClickListener {
            val dataUserIntent = UserModel(
                userData.username,
                userData.name,
                userData.avatar,
                userData.company,
                userData.location,
                userData.repository,
                userData.followers,
                userData.following
            )
            val moveIntent = Intent(it.context, DetailActivity::class.java)
            moveIntent.putExtra(DetailActivity.EXTRA_DETAIL, dataUserIntent)
            it.context.startActivity(moveIntent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(items: ArrayList<UserModel>) {
        userList.clear()
        userList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListUserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(userItem: UserModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(userItem.avatar)
                    .apply(RequestOptions().override(100,100))
                    .into(user_avatar)

                real_name.text = userItem.name
                username.text = userItem.username
                user_repo.text = "${resources.getString(R.string.repository)} ${userItem.repository}"
                user_followers.text = "${resources.getString(R.string.follower)} ${userItem.followers}"
                user_following.text = "${resources.getString(R.string.following)} ${userItem.following}"


            }
        }
    }
}