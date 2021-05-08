package com.example.mygithubproject.modelandservice.API

import com.example.mygithubproject.BuildConfig.GITHUB_TOKEN
import com.example.mygithubproject.modelandservice.data.ModelUserDetailResponse
import com.example.mygithubproject.modelandservice.data.UsersData
import com.example.mygithubproject.modelandservice.dataclass.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("search/users")
    @Headers("Authorization: $GITHUB_TOKEN")
    fun getSearchUsers(
            @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: $GITHUB_TOKEN")
    fun getUserDetail(
            @Path("username") username: String
    ): Call<ModelUserDetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: $GITHUB_TOKEN")
    fun getFollowers(
            @Path("username") username: String
    ): Call<ArrayList<UsersData>>

    @GET("users/{username}/following")
    @Headers("Authorization: $GITHUB_TOKEN")
    fun getFollowing(
            @Path("username") username: String
    ): Call<ArrayList<UsersData>>
}