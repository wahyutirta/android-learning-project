package com.example.mygithubproject.viewmodel


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.mygithubproject.R

import com.example.mygithubproject.services.data.UsersData


class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val dataList = ArrayList<UsersData>()
    var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewgroup.context).inflate(R.layout.item_user, viewgroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {
        viewholder.bind(dataList[position])
    }
    fun setList(users: ArrayList<UsersData>) {
        dataList.clear()
        dataList.addAll(users)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val avatar: ImageView = itemView.findViewById(R.id.avatar)
        private val textID = "Id"
        val id: TextView = itemView.findViewById(R.id.id)
        val name: TextView = itemView.findViewById(R.id.username)

        fun bind(user: UsersData) {
            id.text = StringBuilder(textID).append(" ${user.id}")
            name.text = user.login
            Glide.with(itemView)
                .load(user.avatar_url)
                .placeholder(R.drawable.ic_default)
                .error(R.drawable.ic_connection_error)
                .into(avatar)
            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UsersData)
    }

}