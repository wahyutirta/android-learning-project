package com.example.mygithubproject.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubproject.MainActivity
import com.example.mygithubproject.model.UserModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class ViewModelMainAct : ViewModel() {
    private val token = "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"
    private val mutableUserList = MutableLiveData<ArrayList<UserModel>>()
    private val nonMustableUserList = ArrayList<UserModel>()

    fun loadUser() : LiveData<ArrayList<UserModel>> {
        return mutableUserList
    }
    private fun loadDetail(username: String, context: Context) {
        val httpClient = AsyncHttpClient()
        httpClient.addHeader("Authorization", "token $token")
        httpClient.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/users/$username"

        httpClient.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val result = String(responseBody!!)
                Log.d(MainActivity.TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val userData = UserModel()
                    userData.username = jsonObject.getString("login")
                    userData.name = jsonObject.getString("name")
                    userData.avatar = jsonObject.getString("avatar_url")
                    userData.company = jsonObject.getString("company")
                    userData.location = jsonObject.getString("location")
                    userData.repository = jsonObject.getString("public_repos")
                    userData.followers =  jsonObject.getString("followers")
                    userData.following = jsonObject.getString("following")
                    mutableUserList.postValue(nonMustableUserList)
                    nonMustableUserList.add(userData)
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

    fun loadApiGit(context: Context) {
        val httpClient = AsyncHttpClient()
        httpClient.addHeader("Authorization", "token $token")
        httpClient.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/users"
        httpClient.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val result = String(responseBody!!)
                Log.d(MainActivity.TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username_ = jsonObject.getString("login")
                        loadDetail(username_, context)
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

    fun searchUser(query: String, context: Context) {
        val httpClient = AsyncHttpClient()
        httpClient.addHeader("Authorization", "token $token")
        httpClient.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/search/users?q=$query"

        httpClient.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val result = String(responseBody!!)
                Log.d(MainActivity.TAG, result)
                try {
                    nonMustableUserList.clear()
                    val jsonArray = JSONObject(result)
                    val item = jsonArray.getJSONArray("items")
                    for (i in 0 until item.length()) {
                        val jsonObject = item.getJSONObject(i)
                        val username_ = jsonObject.getString("login")
                        loadDetail(username_, context)
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
}