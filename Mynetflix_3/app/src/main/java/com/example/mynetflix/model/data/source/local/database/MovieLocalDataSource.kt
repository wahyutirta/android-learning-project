package com.example.mynetflix.model.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.mynetflix.model.data.MovieModel


class MovieLocalDataSource private constructor(private val mFilmDao: FilmDao) {

    companion object {
        private var INSTANCE: MovieLocalDataSource? = null

        fun getInstance(filmDao: FilmDao): MovieLocalDataSource =
            INSTANCE ?: MovieLocalDataSource(filmDao).apply {
                INSTANCE = this
            }
    }

    fun setMovieFavorite(movie: MovieModel, NewState: Boolean) {
        movie.favorite = NewState
        mFilmDao.updateMovie(movie)
    }

    fun getAllMovie(): DataSource.Factory<Int, MovieModel> = mFilmDao.getAllMovie()

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieModel> = mFilmDao.getFavoriteMovie()

    fun getDetailMovieById(movieId: String): LiveData<MovieModel> =
        mFilmDao.getDetailWithMovieId(movieId)

    fun insertMoviesList(movies: List<MovieModel>) = mFilmDao.insertMovies(movies)

}