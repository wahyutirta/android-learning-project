package com.example.mygithubuserfavorite.ui.viewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mygithubuserfavorite.R
import com.example.mygithubuserfavorite.databinding.UserItemFavouriteBinding
import com.example.mygithubuserfavorite.favoriteUserDatabase.db.FavouriteUserHelper
import com.example.mygithubuserfavorite.favoriteUserDatabase.entity.FavouriteUser
import com.squareup.picasso.Picasso

class FavoriteUserAdapter(val users: ArrayList<FavouriteUser>) :
    RecyclerView.Adapter<FavoriteUserAdapter.UserViewHolder>() {



    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemFavouriteBinding.bind(itemView)
        private lateinit var favouriteUserHelper: FavouriteUserHelper
        fun bind(favouriteUser: FavouriteUser) {
            binding.apply {
                username.text = favouriteUser.userName
                Picasso.get().load(favouriteUser.avatarURL).into(avatar)
            }
            binding.deleteBtn.setOnClickListener{
                favouriteUserHelper =FavouriteUserHelper.getInstance(itemView.context)
                favouriteUserHelper.open()
                val position = adapterPosition
                val loginKey  = favouriteUser.login
                val result = loginKey?.let { it1 -> favouriteUserHelper.deleteById(it1) }
                if (result!! >0){
                    Toast.makeText(itemView.context, "berhasi;", Toast.LENGTH_SHORT).show()
                    users.removeAt(position)
                    notifyItemRemoved(position-1)
                }else{
                    Toast.makeText(itemView.context, "gagal", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item_favourite, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = this.users.size

}