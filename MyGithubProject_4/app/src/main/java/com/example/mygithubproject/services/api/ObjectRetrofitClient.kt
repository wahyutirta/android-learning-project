package com.example.mygithubproject.services.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ObjectRetrofitClient{
    private const val BASE_API_GITHUB_URL = "https://api.github.com/"
    private val gson = GsonBuilder().setLenient().create()
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_API_GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiInstance: ApiInterface = retrofit.create(ApiInterface::class.java)

}