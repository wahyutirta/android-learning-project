package com.example.mynetflix.DI

import android.content.Context

import com.example.mynetflix.model.data.source.DataRepository

import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): DataRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return DataRepository.getInstance(remoteDataSource)
    }
}