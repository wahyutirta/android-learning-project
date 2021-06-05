package com.example.mygithubproject.viewmodel


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
        val view =
            LayoutInflater.from(viewgroup.context).inflate(R.layout.item_user, viewgroup, false)
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

        lateinit var avatar: ImageView
        lateinit var textID: String
        lateinit var id: TextView
        lateinit var name: TextView
        lateinit var url: TextView

        init {
            avatar = itemView.findViewById(R.id.user_image)
            textID = "Id"
            id = itemView.findViewById(R.id.user_id)
            name = itemView.findViewById(R.id.user_login)
            url = itemView.findViewById(R.id.user_url)

            itemView.setOnClickListener { v: View ->
                Toast.makeText(itemView.context, "user", Toast.LENGTH_SHORT).show()
            }
        }


        fun bind(user: UsersData) {
            id.text = StringBuilder(textID).append(" ${user.id}")
            name.text = user.login
            url.text = user.html_url
            val message = StringBuilder(" ${user.login}").append(" tapped")
            Glide.with(itemView)
                .load(user.avatar_url)
                .placeholder(R.drawable.ic_default)
                .error(R.drawable.ic_connection_error)
                .into(avatar)
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, message, Toast.LENGTH_SHORT).show()
                onItemClickCallback?.onItemClicked(user)

            }

        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UsersData)
    }

}