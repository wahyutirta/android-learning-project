package com.example.mygithubproject.services.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mygithubproject.BuildConfig.TB_NAME

@Dao
interface FavUsersDao {
    @Insert
    suspend fun favoriteAdd(favoriteUsers: FavUsers)

    @Query("SELECT * FROM $TB_NAME")
    fun getFavoriteUser(): LiveData<List<FavUsers>>

    @Query("SELECT count(*) FROM $TB_NAME WHERE $TB_NAME.login = :login OR $TB_NAME.id = :id")
    suspend fun checkUser(login: String, id: Int): Int


    @Query("SELECT * FROM $TB_NAME")
    fun findAllUser(): Cursor

    @Query("DELETE FROM $TB_NAME WHERE $TB_NAME.login = :login OR $TB_NAME.id = :id")
    suspend fun deleteUserFav(login: String, id: Int): Int

}