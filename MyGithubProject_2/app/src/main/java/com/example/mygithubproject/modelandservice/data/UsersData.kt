package com.example.mygithubproject.modelandservice.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersData(
    var login: String? = "",
    var id: Int = 0,
    var avatar_url: String? = "",
) : Parcelable