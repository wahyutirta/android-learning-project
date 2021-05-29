package com.example.mygithubproject.services.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelUserDetailsResponse(
    var login: String? = "",
    var id: Int = 0,
    var name: String? = "",
    var avatar_url: String? = "",
    var html_url: String? = "",
    var followers: String? = "",
    var following: String? = "",
    var company: String? = "",
    var location: String? = "",
    var public_repos: String? = "",
    var followers_url: String? = "",
    var following_url: String? = "",
) : Parcelable