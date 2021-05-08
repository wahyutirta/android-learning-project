package com.example.mygithubuser.userinterface.API

import androidx.viewbinding.BuildConfig
import com.example.mygithubuser.BuildConfig.GITHUB_TOKEN
import retrofit2.http.GET
import retrofit2.http.Header


interface Api {
    private val token = BuildConfig.GITHUB_TOKEN
    @GET("search/users")
    @Header("Authorization: $token")
}