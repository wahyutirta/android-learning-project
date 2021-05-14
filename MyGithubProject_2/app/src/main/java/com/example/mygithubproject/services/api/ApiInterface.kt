package com.example.mygithubproject.services.api

import com.example.mygithubproject.BuildConfig.GIT_API_TOKEN
import com.example.mygithubproject.services.data.ModelUserDetailsResponse
import com.example.mygithubproject.services.data.UsersData
import com.example.mygithubproject.services.dataclass.UserResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("search/users")
    @Headers("Authorization: $GIT_API_TOKEN")
    fun getSearchUsers(
            @Query("q") query: String
    ): Call<UserResponses>

    @GET("users/{username}")
    @Headers("Authorization: $GIT_API_TOKEN")
    fun getUserDetail(
            @Path("username") username: String
    ): Call<ModelUserDetailsResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: $GIT_API_TOKEN")
    fun getFollowers(
            @Path("username") username: String
    ): Call<ArrayList<UsersData>>

    @GET("users/{username}/following")
    @Headers("Authorization: $GIT_API_TOKEN")
    fun getFollowing(
            @Path("username") username: String
    ): Call<ArrayList<UsersData>>
}