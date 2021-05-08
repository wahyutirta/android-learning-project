package com.example.mygithubproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mygithubproject.modelandservice.data.DBUser
import com.example.mygithubproject.modelandservice.data.FavoriteUser
import com.example.mygithubproject.modelandservice.data.FavoriteUserDao

class UserFavoriteViewModel (application: Application) : AndroidViewModel(application) {

    private var userDao: FavoriteUserDao?
    private var userDB: DBUser? = DBUser.getDatabase(application)

    init {
        userDao = userDB?.favoriteUserDao()
    }

    fun getFavUser(): LiveData<List<FavoriteUser>>? {

        return userDao?.getFavoriteUser()

    }

}