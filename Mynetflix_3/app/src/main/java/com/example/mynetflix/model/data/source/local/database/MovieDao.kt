package com.example.mynetflix.model.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.BuildConfig.TB_MOVIE



@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    fun insertMovie(movie: MovieModel): Long

    @Insert(onConflict = REPLACE)
    fun insertMovies(movies: List<MovieModel>)

    @Update
    fun updateMovie(movie: MovieModel)

    @Query("DELETE FROM $TB_MOVIE WHERE id = :id")
    fun deleteMovieById(id: String)

    @Query("SELECT * FROM $TB_MOVIE")
    fun getAllMovie(): androidx.paging.DataSource.Factory<Int, MovieModel>

    @Transaction
    @Query("SELECT * FROM $TB_MOVIE WHERE id = :movieId")
    fun getDetailWithMovieId(movieId: String): LiveData<MovieModel>

    @Query("SELECT * FROM $TB_MOVIE WHERE favorite = 1")
    fun getFavoriteMovie(): androidx.paging.DataSource.Factory<Int, MovieModel>

}