package com.example.mygithubproject.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubproject.model.UserFollowerModel
import com.example.mygithubproject.view.FollowerFragment
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import kotlin.Exception


class ViewModelFollower : ViewModel() {
    private val token = "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"
    private val mutableFollowerList = MutableLiveData<ArrayList<UserFollowerModel>>()
    private val nonMutableFollowerList = ArrayList<UserFollowerModel>()

    fun loadListFollowers(): LiveData<ArrayList<UserFollowerModel>> {
        return mutableFollowerList
    }

    fun loadApiGitFollower(context: Context, id: String) {
        val httpClient = AsyncHttpClient()
        httpClient.addHeader("Authorization", "token $token")
        httpClient.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/users/$id/followers"

        httpClient.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val result = String(responseBody!!)
                Log.d(FollowerFragment.TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username_1 = jsonObject.getString("login")
                        loadDetailFollower(username_1, context)
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun loadDetailFollower(username: String, context: Context) {
        val httpClient = AsyncHttpClient()
        httpClient.addHeader("Authorization", "token $token")
        httpClient.addHeader("User-Agent", "request")

        val clientUrl = "https://api.github.com/users/$username"

        httpClient.get(clientUrl, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val result = String(responseBody!!)
                Log.d(FollowerFragment.TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val followerItem = UserFollowerModel()
                    followerItem.username = jsonObject.getString("login")
                    followerItem.name = jsonObject.getString("name")
                    followerItem.avatar = jsonObject.getString("avatar_url")
                    followerItem.company = jsonObject.getString("company")
                    followerItem.location = jsonObject.getString("location")
                    followerItem.repository = jsonObject.getString("public_repos")
                    followerItem.followers =  jsonObject.getString("followers")
                    followerItem.following = jsonObject.getString("following")
                    mutableFollowerList.postValue(nonMutableFollowerList)
                    nonMutableFollowerList.add(followerItem)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}