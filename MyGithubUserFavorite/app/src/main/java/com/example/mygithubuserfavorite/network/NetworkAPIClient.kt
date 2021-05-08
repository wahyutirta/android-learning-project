package com.example.mygithubuserfavorite.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkAPIClient {

    private const val BASE_URL = "https://api.github.com/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService : NetworkAPIService = getRetrofit().create(NetworkAPIService::class.java)
}