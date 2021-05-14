package com.example.mygithubproject.services.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tb_favUsers")
data class FavUsers(
        val login: String,
        @PrimaryKey
        val id: Int,
        val avatarUrl: String,
) : Serializable