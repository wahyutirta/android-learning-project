package com.example.consumerapp.services


import com.example.consumerapp.model.UsersData
import com.example.consumerapp.model.ModelUserDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import com.example.consumerapp.BuildConfig.GIT_API_TOKEN
import retrofit2.Call


interface ApiInterface {



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