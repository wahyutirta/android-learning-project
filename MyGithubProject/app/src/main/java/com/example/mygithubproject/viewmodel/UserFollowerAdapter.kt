package com.example.mygithubproject.viewmodel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mygithubproject.R
import com.example.mygithubproject.model.UserFollowerModel
import kotlinx.android.synthetic.main.user_item.view.*

class UserFollowerAdapter(private val followerList: ArrayList<UserFollowerModel>) : RecyclerView.Adapter<UserFollowerAdapter.ListUserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFollowerAdapter.ListUserHolder {
        return ListUserHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserFollowerAdapter.ListUserHolder, position: Int) {
        holder.bind(followerList[position])
    }

    override fun getItemCount(): Int {
        return followerList.size
    }

    fun loadData (item: ArrayList<UserFollowerModel>) {
        followerList.clear()
        followerList.addAll(item)
        notifyDataSetChanged()
    }

    inner class ListUserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(userFollower: UserFollowerModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(userFollower.avatar)
                    .apply(RequestOptions().override(100, 100))
                    .into(user_avatar)

                real_name.text = userFollower.name
                username.text = userFollower.username
                user_repo.text =  "${resources.getString(R.string.repository)} ${userFollower.repository}"
                user_followers.text = "${resources.getString(R.string.follower)} ${userFollower.followers}"
                user_following.text = "${resources.getString(R.string.following)} ${userFollower.following}"
            }
        }
    }
}