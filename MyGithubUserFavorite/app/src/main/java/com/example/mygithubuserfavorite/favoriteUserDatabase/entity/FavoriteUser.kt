package com.example.mygithubuserfavorite.favoriteUserDatabase.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavouriteUser (
    var login : String? = null,
    var avatarURL : String? = null,
    var userName : String? = null
) : Parcelable