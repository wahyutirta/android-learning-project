package com.example.mynetflix.di

import android.content.Context


import com.example.mynetflix.model.data.source.remote.repository.MovieRepository
import com.example.mynetflix.model.data.source.remote.repository.TvShowRepository
import com.example.mynetflix.model.data.source.local.database.FilmDatabase
import com.example.mynetflix.model.data.source.local.database.MovieLocalDataSource
import com.example.mynetflix.model.data.source.local.database.TvShowLocalDataSource


import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.utils.AppExecutors
import com.example.mynetflix.model.utils.JsonHelper

object Injection {

    fun provideMovieRepository(context: Context): MovieRepository {

        val database = FilmDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val movieLocalDataSource = MovieLocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return MovieRepository.getInstance(
            remoteDataSource,
            movieLocalDataSource,
            appExecutors
        )
    }

    fun provideTvShowRepository(context: Context): TvShowRepository {

        val database = FilmDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val tvShowLocalDataSource = TvShowLocalDataSource.getInstance(database.tvShowDao())
        val appExecutors = AppExecutors()
        return TvShowRepository.getInstance(
            remoteDataSource,
            tvShowLocalDataSource,
            appExecutors
        )
    }

}