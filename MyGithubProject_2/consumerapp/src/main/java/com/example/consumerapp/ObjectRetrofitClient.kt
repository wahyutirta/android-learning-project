package com.example.consumerapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ObjectRetrofitClient{
    private const val BASE_GITHUB_URL = "https://api.github.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance: ApiInterface = retrofit.create(ApiInterface::class.java)

}