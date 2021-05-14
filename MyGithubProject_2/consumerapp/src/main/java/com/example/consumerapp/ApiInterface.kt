package com.example.consumerapp


import com.example.consumerapp.model.UsersData
import com.example.consumerapp.UserResponses
import com.example.consumerapp.ModelUserDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.consumerapp.BuildConfig.GIT_API_TOKEN
import retrofit2.Call


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