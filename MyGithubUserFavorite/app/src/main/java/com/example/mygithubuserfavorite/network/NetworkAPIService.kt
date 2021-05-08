package com.example.mygithubuserfavorite.network

import com.example.mygithubuserfavorite.data.model.ModelSearchUser
import com.example.mygithubuserfavorite.data.model.ModelUserDetail
import com.example.mygithubuserfavorite.data.model.ModelUserFollowersItem
import com.example.mygithubuserfavorite.data.model.ModelUserFollowingItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkAPIService {
    @GET("search/users?")//user search endpoint
    suspend fun getUserList(
        @Query("q") q: String
    ): ModelSearchUser

    @GET("users/{username}")// user detail endpoint
    suspend fun getDetailUser(
        @Path("username") username: String
    ): ModelUserDetail

    @GET("users/{username}/followers")//user follower endpoint
    suspend fun getFollowerUser(
        @Path("username") username: String
    ): List<ModelUserFollowersItem>

    @GET("users/{username}/following")//following of user endpoint
    suspend fun getFollowingUser(
        @Path("username") username: String
    ): List<ModelUserFollowingItem>
}