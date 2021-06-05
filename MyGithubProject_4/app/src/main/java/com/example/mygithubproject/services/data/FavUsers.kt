package com.example.mygithubproject.services.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import com.example.mygithubproject.BuildConfig.TB_NAME

@Entity(tableName = TB_NAME)
@Parcelize
data class FavUsers(
        val login: String,
        @PrimaryKey
        val id: Int,
        val avatarUrl: String,
        var htmlUrl: String
) : Serializable, Parcelable