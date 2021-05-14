package com.example.mygithubproject.viewmodel


import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.mygithubproject.services.api.ObjectRetrofitClient
import com.example.mygithubproject.services.data.UsersData
import com.example.mygithubproject.services.dataclass.UserResponses

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUserMain : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<UsersData>>()
    var status = MutableLiveData<Boolean?>()

    fun setSearchQuery(query: String) {
        ObjectRetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponses> {
                override fun onResponse(
                        call: Call<UserResponses>,
                        responses: Response<UserResponses>
                ) {
                    if (responses.isSuccessful) {
                        listUsers.postValue(responses.body()?.items)
                        status.value = true
                    }
                }

                override fun onFailure(call: Call<UserResponses>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                    status.value = false
                }

            })
    }

    fun getSearchQuery(): LiveData<ArrayList<UsersData>> {
        return listUsers
    }

}