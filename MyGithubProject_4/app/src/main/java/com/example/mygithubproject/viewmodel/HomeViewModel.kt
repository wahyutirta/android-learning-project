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

class HomeViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<UsersData>>()
    var flag = MutableLiveData<Boolean?>()

    fun loadSearchQuery(query: String) {
        ObjectRetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponses> {
                override fun onResponse(
                    call: Call<UserResponses>,
                    responses: Response<UserResponses>
                ) {
                    when (responses.isSuccessful) {
                        true -> {

                            listUsers.postValue(responses.body()?.items)
                            flag.value = true
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponses>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                    flag.value = false
                }

            })
    }

    fun takeSearchQuery(): LiveData<ArrayList<UsersData>> {
        return listUsers
    }

}