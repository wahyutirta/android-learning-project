package com.example.mygithubproject.viewmodel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mygithubproject.R
import com.example.mygithubproject.model.UserFollowingModel
import kotlinx.android.synthetic.main.user_item.view.*

class UserFollowingAdapter(private val followingList: ArrayList<UserFollowingModel>) : RecyclerView.Adapter<UserFollowingAdapter.ListUserHolder>() {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): UserFollowingAdapter.ListUserHolder {
        return ListUserHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserFollowingAdapter.ListUserHolder, position: Int) {
        holder.bind(followingList[position])
    }

    override fun getItemCount(): Int {
        return followingList.size
    }

    fun loadData(item: ArrayList<UserFollowingModel>) {
        followingList.clear()
        followingList.addAll(item)
        notifyDataSetChanged()
    }

    inner class ListUserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(userFollowing: UserFollowingModel) {
            with(itemView) {
                Glide.with(itemView.context)
                        .load(userFollowing.avatar)
                        .apply(RequestOptions().override(100, 100))
                        .into(user_avatar)

                real_name.text = userFollowing.name
                username.text = userFollowing.username
                user_repo.text = "${resources.getString(R.string.repository)} ${userFollowing.repository}"
                user_followers.text = "${resources.getString(R.string.follower)} ${userFollowing.followers}"
                user_following.text = "${resources.getString(R.string.following)} ${userFollowing.following}"
            }
        }
    }
}