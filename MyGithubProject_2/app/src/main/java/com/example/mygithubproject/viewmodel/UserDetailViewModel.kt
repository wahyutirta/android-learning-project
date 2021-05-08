package com.example.mygithubproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mygithubproject.modelandservice.API.ObjectRetrofitClient
import com.example.mygithubproject.modelandservice.data.DBUser
import com.example.mygithubproject.modelandservice.data.FavoriteUser
import com.example.mygithubproject.modelandservice.data.FavoriteUserDao
import com.example.mygithubproject.modelandservice.data.ModelUserDetailResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel(application: Application) : AndroidViewModel(application) {

    val user = MutableLiveData<ModelUserDetailResponse>()
    var status = MutableLiveData<Boolean?>()

    private var userDao: FavoriteUserDao?
    private var userDB: DBUser? = DBUser.getDatabase(application)

    init {
        userDao = userDB?.favoriteUserDao()
    }

    fun setUserDetail(username: String) {
        ObjectRetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<ModelUserDetailResponse> {
                override fun onResponse(
                    call: Call<ModelUserDetailResponse>,
                    response: Response<ModelUserDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                        status.value = true
                    }
                }

                override fun onFailure(call: Call<ModelUserDetailResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                    status.value = false
                }
            })
    }

    fun getUserDetail(): LiveData<ModelUserDetailResponse> {
        return user
    }

    fun addToFav(username: String, id: Int, avatar_url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavoriteUser(
                username,
                id,
                avatar_url
            )
            userDao?.favoriteAdd(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun userRemoveFromFav(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.deleteUserFav(id)
        }
    }

}