package com.example.mygithubproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mygithubproject.services.data.DBUser
import com.example.mygithubproject.services.data.FavUsers
import com.example.mygithubproject.services.data.FavUsersDao

class ViewModelUserFavorite (application: Application) : AndroidViewModel(application) {
    private var userDB: DBUser? = DBUser.getDatabase(application)
    private var Dao: FavUsersDao?

    init {
        Dao = userDB?.favoriteUsersDao()
    }

    fun getFavUsers(): LiveData<List<FavUsers>>? {

        return Dao?.getFavoriteUser()

    }

}