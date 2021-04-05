package com.example.githubsubmission1.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var username: String? = "",
    var name: String? = "",
    var avatar: Int = 0,
    var follower: String? = "",
    var following: String? = "",
    var company: String? = "",
    var location: String? = "",
    var repository: String? = "",
) : Parcelable
