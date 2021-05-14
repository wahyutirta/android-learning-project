package com.example.mygithubproject.services.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersData(
    var login: String? = "",
    var id: Int = 0,
    var avatar_url: String? = "",
) : Parcelable