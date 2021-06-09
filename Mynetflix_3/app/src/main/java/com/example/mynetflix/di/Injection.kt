package com.example.mynetflix.di

import android.content.Context

import com.example.mynetflix.model.data.source.DataRepository
import com.example.mynetflix.model.data.source.local.database.FilmDatabase
import com.example.mynetflix.model.data.source.local.database.MovieLocalDataSource
import com.example.mynetflix.model.data.source.local.database.TvShowLocalDataSource


import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.utils.AppExecutors
import com.example.mynetflix.model.utils.JsonHelper

object Injection {

        fun provideRepository(context: Context): DataRepository {

        val database = FilmDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val tvShowLocalDataSource = TvShowLocalDataSource.getInstance(database.filmDao())
        val movieLocalDataSource = MovieLocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()
        return DataRepository.getInstance(remoteDataSource, movieLocalDataSource, tvShowLocalDataSource,appExecutors )
    }

}