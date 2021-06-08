package com.example.mynetflix.model.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel


class LocalDataSource private constructor(private val mFilmDao: FilmDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao).apply {
                INSTANCE = this
            }
    }

    fun setFavoriteMovie(movie: MovieModel) {
        mFilmDao.insert(movie)
    }


    fun setMovieFavorite(movie: MovieModel, NewState: Boolean) {
        movie.favorite = NewState
        mFilmDao.updateMovie(movie)
    }

    fun setTvShowFavorite(tvShow: TvShowModel, NewState: Boolean) {
        tvShow.favorite = NewState
        mFilmDao.updateTvShow(tvShow)
    }

    fun getAllMovie(): DataSource.Factory<Int, MovieModel> = mFilmDao.getAllMovie()

    fun getAllTvShow(): DataSource.Factory<Int, TvShowModel> = mFilmDao.getAllTvShow()

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieModel> = mFilmDao.getFavoriteMovie()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowModel> = mFilmDao.getFavoriteTvShow()

    fun getDetailMovieById(movieId: String): LiveData<MovieModel> =
        mFilmDao.getDetailWithMovieId(movieId)

    fun getDetailTvShowById(tvShowId: String): LiveData<TvShowModel> =
        mFilmDao.getDetailWithTvId(tvShowId)

    fun insertMoviesList(movies: List<MovieModel>) = mFilmDao.insertMovies(movies)

    fun insertTvShowList(tvShow: List<TvShowModel>) = mFilmDao.insertTvShow(tvShow)



}