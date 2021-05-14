package com.example.consumerapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUserDetail(application: Application) : AndroidViewModel(application) {

    val user = MutableLiveData<ModelUserDetailsResponse>()
    var status = MutableLiveData<Boolean?>()



    fun setDetail(username: String) {
        ObjectRetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<ModelUserDetailsResponse> {
                override fun onResponse(
                    call: Call<ModelUserDetailsResponse>,
                    response: Response<ModelUserDetailsResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                        status.value = true
                    }
                }

                override fun onFailure(call: Call<ModelUserDetailsResponse>, t: Throwable) {
                    t.message?.let { Log.d("Fail", it) }
                    status.value = false
                }
            })
    }

    fun getDetail(): LiveData<ModelUserDetailsResponse> {
        return user
    }

}