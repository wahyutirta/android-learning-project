package com.example.mynetflix.model.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.mynetflix.model.data.MovieModel


class MovieLocalDataSource private constructor(private val managerMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: MovieLocalDataSource? = null

        fun getInstance(movieDao: MovieDao): MovieLocalDataSource =
            INSTANCE ?: MovieLocalDataSource(movieDao).apply {
                INSTANCE = this
            }
    }

    fun getAllMovie(): DataSource.Factory<Int, MovieModel> = managerMovieDao.getAllMovie()

    fun getDetailMovieById(movieId: String): LiveData<MovieModel> =
        managerMovieDao.getDetailWithMovieId(movieId)

    fun insertMoviesList(movies: List<MovieModel>) = managerMovieDao.insertMovies(movies)

    fun setMovieFavorite(movie: MovieModel, NewState: Boolean) {
        movie.favorite = NewState
        managerMovieDao.updateMovie(movie)
    }

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieModel> = managerMovieDao.getFavoriteMovie()

}