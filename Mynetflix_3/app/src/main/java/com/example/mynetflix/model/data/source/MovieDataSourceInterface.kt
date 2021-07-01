package com.example.mynetflix.model.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.mynetflix.model.data.MovieModel

import com.example.mynetflix.vo.Resource


interface MovieDataSourceInterface {

    fun getAllMoviesResource(): LiveData<Resource<PagedList<MovieModel>>>

    fun getAllMovie(): LiveData<PagedList<MovieModel>>

    fun getMoviesDetail(movieId: String): LiveData<Resource<MovieModel>>

    fun getDetailMovieById(movieId: String): LiveData<MovieModel>

    fun setMovieFavoriteState(movie: MovieModel, NewState: Boolean)

    fun getFavoriteMovie(): LiveData<PagedList<MovieModel>>

}