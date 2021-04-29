package com.example.mygithubproject.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubproject.model.UserFollowingModel
import com.example.mygithubproject.view.FollowingFragment
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class ViewModelFollowing : ViewModel() {
    private val token = "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"
    private val mutableFollowingList = MutableLiveData<ArrayList<UserFollowingModel>>()
    private val nonMutableFollowingList = ArrayList<UserFollowingModel>()

    fun loadListFollowing(): LiveData<ArrayList<UserFollowingModel>> {
        return mutableFollowingList
    }

    fun loadApiGitFollowing(context: Context, id: String) {
        val httpClient = AsyncHttpClient()
        httpClient.addHeader("Authorization", "token $token")
        httpClient.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/users/$id/following"

        httpClient.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val result = String(responseBody!!)
                Log.d(FollowingFragment.TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username_1 = jsonObject.getString("login")
                        loadDetailFollowing(username_1, context)
                    }
                }catch (e: Exception) {
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

    private fun loadDetailFollowing(username: String, context: Context) {
        val httpClient = AsyncHttpClient()
        httpClient.addHeader("Authorization", "token $token")
        httpClient.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/users/$username"

        httpClient.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val result = String(responseBody!!)
                Log.d(FollowingFragment.TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val followingItem = UserFollowingModel()
                    followingItem.username = jsonObject.getString("login")
                    followingItem.name = jsonObject.getString("name")
                    followingItem.avatar = jsonObject.getString("avatar_url")
                    followingItem.company = jsonObject.getString("company")
                    followingItem.location = jsonObject.getString("location")
                    followingItem.repository = jsonObject.getString("public_repos")
                    followingItem.followers = jsonObject.getString("followers")
                    followingItem.following = jsonObject.getString("following")
                    mutableFollowingList.postValue(nonMutableFollowingList)
                    nonMutableFollowingList.add(followingItem)
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