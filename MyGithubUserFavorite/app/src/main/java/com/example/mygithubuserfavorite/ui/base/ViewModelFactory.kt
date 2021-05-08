package com.example.mygithubuserfavorite.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygithubuserfavorite.data.repos.MainRepository
import com.example.mygithubuserfavorite.network.APIHelper
import com.example.mygithubuserfavorite.ui.viewModel.MainViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiHelper: APIHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unkown Class")
    }

}