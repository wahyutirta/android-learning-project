package com.example.mygithubproject.services.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "tb_favUsers")
@Parcelize
data class FavUsers(
        val login: String,
        @PrimaryKey
        val id: Int,
        val avatarUrl: String,
        var html_url: String,
) : Serializable, Parcelable