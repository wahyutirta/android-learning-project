package com.example.mynetflix.model.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

import com.example.mynetflix.BuildConfig.TB_TVSHOW
import com.example.mynetflix.model.data.TvShowModel


@Dao
interface TvShowDao {

    @Insert(onConflict = REPLACE)
    fun insertTvShow(tvShow: List<TvShowModel>)

    @Insert(onConflict = REPLACE)
    fun insertTvShow(tvShow: TvShowModel): Long

    @Update
    fun updateTvShow(tvShow: TvShowModel)

    @Query("DELETE FROM $TB_TVSHOW WHERE id = :id")
    fun deleteTvShowById(id: String)

    @Query("SELECT * FROM $TB_TVSHOW")
    fun getAllTvShow(): androidx.paging.DataSource.Factory<Int, TvShowModel>

    @Transaction
    @Query("SELECT * FROM $TB_TVSHOW WHERE id = :tvShowId")
    fun getDetailWithTvId(tvShowId: String): LiveData<TvShowModel>

    @Query("SELECT * FROM $TB_TVSHOW WHERE favorite = 1")
    fun getFavoriteTvShow(): androidx.paging.DataSource.Factory<Int, TvShowModel>

}