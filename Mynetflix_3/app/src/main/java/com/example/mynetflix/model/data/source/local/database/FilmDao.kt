package com.example.mynetflix.model.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.BuildConfig.TB_MOVIE
import com.example.mynetflix.BuildConfig.TB_TVSHOW
import com.example.mynetflix.model.data.TvShowModel


@Dao
interface FilmDao {

    @Insert(onConflict = REPLACE)
    fun insertMovie(movie: MovieModel): Long

    @Query("DELETE FROM $TB_MOVIE WHERE id = :id")
    fun deleteMovieById(id: String)

    @Query("SELECT * FROM $TB_MOVIE")
    fun getAllMovie(): androidx.paging.DataSource.Factory<Int, MovieModel>

    @Transaction
    @Query("SELECT * FROM $TB_MOVIE WHERE id = :movieId")
    fun getDetailWithMovieId(movieId: String): LiveData<MovieModel>

    @Insert(onConflict = REPLACE)
    fun insertMovies(movies: List<MovieModel>)

    @Update
    fun updateMovie(movie: MovieModel)

    @Update
    fun updateMovies(movie: MovieModel)

    @Query("SELECT * FROM $TB_MOVIE WHERE favorite = 1")
    fun getFavoriteMovie(): androidx.paging.DataSource.Factory<Int, MovieModel>

    @Query("SELECT * FROM $TB_TVSHOW WHERE favorite = 1")
    fun getFavoriteTvShow(): androidx.paging.DataSource.Factory<Int, TvShowModel>

    @Update
    fun updateTvShow(tvShow: TvShowModel)

    @Insert(onConflict = REPLACE)
    fun insertTvShow(tvShow: List<TvShowModel>)

    @Insert(onConflict = REPLACE)
    fun insertTvShow(tvShow: TvShowModel): Long

    @Query("SELECT * FROM $TB_TVSHOW WHERE id = :id")
    fun getTvShowById(id: String?): TvShowModel?

    @Query("DELETE FROM $TB_TVSHOW WHERE id = :id")
    fun deleteTvShowById(id: String)

    @Query("SELECT * FROM $TB_TVSHOW")
    fun getAllTvShow(): androidx.paging.DataSource.Factory<Int, TvShowModel>

    @Transaction
    @Query("SELECT * FROM $TB_TVSHOW WHERE id = :tvShowId")
    fun getDetailWithTvId(tvShowId: String): LiveData<TvShowModel>

}