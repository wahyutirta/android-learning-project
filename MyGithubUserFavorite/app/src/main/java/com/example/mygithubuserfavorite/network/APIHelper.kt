package com.example.mygithubuserfavorite.network

class APIHelper(private val networkApiService: NetworkAPIService) {

    suspend fun getUser(query: String) = networkApiService.getUserList(query)
    suspend fun getUserDataList(query: String) = networkApiService.getDetailUser(query)
    suspend fun getFollower(query: String) = networkApiService.getFollowerUser(query)
    suspend fun getFollowing(query: String) = networkApiService.getFollowingUser(query)
}