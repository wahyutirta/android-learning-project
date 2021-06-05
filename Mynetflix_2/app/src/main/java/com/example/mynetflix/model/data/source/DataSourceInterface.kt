package com.example.mynetflix.model.data.source

import androidx.lifecycle.LiveData
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel


interface DataSourceInterface {

    fun getAllMovies(): LiveData<List<MovieModel>>

    fun getAllTvShow(): LiveData<List<TvShowModel>>

    fun getMoviesDetail(movieId: String): LiveData<MovieModel>

    fun getTvShowDetail(tvShowId: String): LiveData<TvShowModel>

}