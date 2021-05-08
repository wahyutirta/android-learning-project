package com.example.mygithubuserfavorite.favoriteUserDatabase.helper

import android.database.Cursor
import com.example.mygithubuserfavorite.favoriteUserDatabase.db.DatabaseContract
import com.example.mygithubuserfavorite.favoriteUserDatabase.db.DatabaseContract.UserColumns.Companion.AVATAR
import com.example.mygithubuserfavorite.favoriteUserDatabase.db.DatabaseContract.UserColumns.Companion.USERNAME
import com.example.mygithubuserfavorite.favoriteUserDatabase.entity.FavouriteUser

object MappingHelper {
    fun mapCursorToArrayList(usersCursor: Cursor?): ArrayList<FavouriteUser>{
        val userList = ArrayList<FavouriteUser>()
        usersCursor?.apply {
            while (moveToNext()){
                val login = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.LOGIN))
                val avatarUrl = getString(getColumnIndexOrThrow(AVATAR))
                val userName = getString(getColumnIndexOrThrow(USERNAME))
                userList.add(FavouriteUser(login,avatarUrl,userName))
            }
        }
        return userList
    }


}