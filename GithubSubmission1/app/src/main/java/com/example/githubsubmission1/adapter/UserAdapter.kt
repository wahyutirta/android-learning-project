package com.example.githubsubmission1.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsubmission1.R
import com.example.githubsubmission1.model.UserModel
import com.example.githubsubmission1.view.DetailUser

class UserAdapter (var dataList: ArrayList<UserModel>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    //callback
    private lateinit var onItemClickCallback: OnItemClickCallback
    //callback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewgroup.context).inflate(R.layout.item_user, viewgroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {
        val userdata = dataList[position]
        viewholder.username.text = userdata.username
        viewholder.name.text = userdata.name
        viewholder.avatar.setImageResource(userdata.avatar)
        viewholder.follower.text = "Follower ${userdata.follower}"
        viewholder.following.text = "Following ${userdata.following}"
        viewholder.company.text = userdata.company
        viewholder.location.text = userdata.location
        viewholder.repository.text = "Repo ${userdata.repository}"

        //Move To Detail Activity
        val dataUserIntent = UserModel(
            userdata.username,
            userdata.name,
            userdata.avatar,
            userdata.follower,
            userdata.following,
            userdata.company,
            userdata.location,
            userdata.repository
        )

        val SelectedUser = viewholder.itemView.context

        viewholder.itemView.setOnClickListener {
            //
            onItemClickCallback.onItemClicked(dataList[viewholder.adapterPosition])
            //
            val moveSpec = Intent(SelectedUser, DetailUser::class.java)
            moveSpec.putExtra(DetailUser.EXTRA_DETAIL, dataUserIntent)
            SelectedUser.startActivity(moveSpec)
        }
    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.username)
        val name: TextView = itemView.findViewById(R.id.name)
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
        val follower: TextView = itemView.findViewById(R.id.follower)
        val following: TextView = itemView.findViewById(R.id.following)
        val company: TextView = itemView.findViewById(R.id.company)
        val location: TextView = itemView.findViewById(R.id.location)
        val repository: TextView = itemView.findViewById(R.id.repository)

    }

    fun filterList(filteredCourseList: ArrayList<UserModel>) {
        this.dataList = filteredCourseList
        notifyDataSetChanged()
    }
    //callback
    interface OnItemClickCallback {
        fun onItemClicked(data: UserModel)
    }

}