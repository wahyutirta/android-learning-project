package com.example.mygithubproject.services.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavUsersDao {
    @Insert
    suspend fun favoriteAdd(favoriteUsers: FavUsers)

    @Query("SELECT * FROM tb_favUsers")
    fun getFavoriteUser(): LiveData<List<FavUsers>>

    @Query("SELECT count(*) FROM tb_favUsers WHERE tb_favUsers.login = :login OR tb_favUsers.id = :id")
    suspend fun checkUser(login: String, id: Int): Int

    @Query("DELETE FROM tb_favUsers WHERE tb_favUsers.login = :login OR tb_favUsers.id = :id")
    suspend fun deleteUserFav(login: String, id: Int): Int

    @Query("SELECT * FROM tb_favUsers")
    fun findAllUser(): Cursor

}