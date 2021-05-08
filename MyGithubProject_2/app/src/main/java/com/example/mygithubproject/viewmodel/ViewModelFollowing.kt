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

class ViewModelFollowing : ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<UsersData>>()

    fun setListFollowing(username: String) {
        ObjectRetrofitClient.apiInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<UsersData>> {
                override fun onResponse(
                    call: Call<ArrayList<UsersData>>,
                    response: Response<ArrayList<UsersData>>
                ) {
                    if (response.isSuccessful) {
                        listFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UsersData>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getListFollowing(): LiveData<ArrayList<UsersData>> {
        return listFollowing
    }
}