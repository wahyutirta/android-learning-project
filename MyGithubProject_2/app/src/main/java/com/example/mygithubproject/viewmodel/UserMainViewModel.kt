package com.example.mygithubproject.viewmodel


import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.mygithubproject.modelandservice.API.ObjectRetrofitClient
import com.example.mygithubproject.modelandservice.data.UsersData
import com.example.mygithubproject.modelandservice.dataclass.UserResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserMainViewModel : ViewModel() {

    val listUser = MutableLiveData<ArrayList<UsersData>>()
    var status = MutableLiveData<Boolean?>()

    fun setSearchQuery(query: String) {
        ObjectRetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUser.postValue(response.body()?.items)
                        status.value = true
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                    status.value = false
                }

            })
    }

    fun getSearchQuery(): LiveData<ArrayList<UsersData>> {
        return listUser
    }

}