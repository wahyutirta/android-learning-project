package com.example.mygithubproject.modelandservice.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelUserDetailResponse(
    var id: Int = 0,
    var login: String? = "",
    var name: String? = "",
    var avatar_url: String? = "",
    var followers: String? = "",
    var following: String? = "",
    var company: String? = "",
    var location: String? = "",
    var public_repos: String? = "",
    var following_url: String? = "",
    var followers_url: String? = "",
) : Parcelable