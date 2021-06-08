package com.example.mynetflix.model.data.source

import androidx.lifecycle.LiveData
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel


interface DataSourceInterface {

    fun getMoviesDetail(movieId: String): LiveData<MovieModel>

    fun getTvShowDetail(tvShowId: String): LiveData<TvShowModel>

    fun getMovies(): LiveData<List<MovieModel>>

    fun getTvShow(): LiveData<List<TvShowModel>>

}