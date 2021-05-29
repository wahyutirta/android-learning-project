package com.example.consumerapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.consumerapp.database.DatabaseContract
import com.example.consumerapp.database.MappingHelper
import com.example.consumerapp.model.UsersData

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var list = MutableLiveData<ArrayList<UsersData>>()

    fun setFavorite(context: Context) {
        val cursor = context.contentResolver.query(
            DatabaseContract.FavUserColumns.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val listConvert = MappingHelper.mapCursorToArrayList(cursor)
        list.postValue(listConvert)
    }

    fun getFavUser(): LiveData<ArrayList<UsersData>> {
        return list
    }

}