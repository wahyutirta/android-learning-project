package com.example.mygithubproject.viewmodel


import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.mygithubproject.modelandservice.API.ObjectRetrofitClient
import com.example.mygithubproject.modelandservice.data.UsersData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ViewModelFollower : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<UsersData>>()

    fun setListFollowers(username: String) {
        ObjectRetrofitClient.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<UsersData>> {
                override fun onResponse(
                    call: Call<ArrayList<UsersData>>,
                    response: Response<ArrayList<UsersData>>
                ) {
                    if (response.isSuccessful) {
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UsersData>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }
            })
    }

    fun getListFollowers(): LiveData<ArrayList<UsersData>> {
        return listFollowers
    }

}