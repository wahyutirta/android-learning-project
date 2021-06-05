package com.example.mygithubproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mygithubproject.services.api.ObjectRetrofitClient
import com.example.mygithubproject.services.data.DBUser
import com.example.mygithubproject.services.data.FavUsers
import com.example.mygithubproject.services.data.FavUsersDao
import com.example.mygithubproject.services.data.ModelUserDetailsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    val user = MutableLiveData<ModelUserDetailsResponse>()
    var status = MutableLiveData<Boolean?>()

    private var Dao: FavUsersDao?
    private var userDB: DBUser? = DBUser.getDatabase(application)

    init {
        Dao = userDB?.favoriteUsersDao()
    }


    fun takeDetail(): LiveData<ModelUserDetailsResponse> {
        return user
    }

    fun addUser(username: String, id: Int, avatar_url: String, html_url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavUsers(
                username,
                id,
                avatar_url,
                html_url
            )
            Dao?.favoriteAdd(user)
        }
    }

    suspend fun checkUser(login: String, id: Int) = Dao?.checkUser(login, id)

    fun removeUser(username: String, id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            Dao?.deleteUserFav(username, id)
        }
    }

    fun loadDetail(username: String) {
        ObjectRetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<ModelUserDetailsResponse> {
                override fun onResponse(
                    call: Call<ModelUserDetailsResponse>,
                    response: Response<ModelUserDetailsResponse>
                ) {
                    when (response.isSuccessful) {
                        true -> {
                            user.postValue(response.body())
                            status.value = true
                        }
                    }
                }

                override fun onFailure(call: Call<ModelUserDetailsResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                    status.value = false
                }
            })
    }

}