package com.example.mynetflix.model.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.vo.Resource


interface DataSourceInterface {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieModel>>>

    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowModel>>>

    fun getMoviesDetail(movieId: String): LiveData<Resource<MovieModel>>

    fun getTvShowDetail(tvShowId: String): LiveData<Resource<TvShowModel>>

    fun getAllMovie(): LiveData<PagedList<MovieModel>>

    fun getAllTvShows(): LiveData<PagedList<TvShowModel>>

    fun setTvShowFavorite(tvShow: TvShowModel, NewState: Boolean)

    fun setMovieFavorite(movie: MovieModel, NewState: Boolean)

    fun getFavoriteMovie(): LiveData<PagedList<MovieModel>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowModel>>

    fun getDetailTvShowById(tvShowId: String): LiveData<TvShowModel>

    fun getDetailMovieById(movieId: String): LiveData<MovieModel>

}