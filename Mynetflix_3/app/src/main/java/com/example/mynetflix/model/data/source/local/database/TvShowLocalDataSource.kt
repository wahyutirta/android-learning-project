package com.example.mynetflix.model.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel

class TvShowLocalDataSource private constructor(private val mFilmDao: FilmDao) {

    companion object {
        private var INSTANCE: TvShowLocalDataSource? = null

        fun getInstance(filmDao: FilmDao): TvShowLocalDataSource =
            INSTANCE ?: TvShowLocalDataSource(filmDao).apply {
                INSTANCE = this
            }
    }


    fun setTvShowFavorite(tvShow: TvShowModel, NewState: Boolean) {
        tvShow.favorite = NewState
        mFilmDao.updateTvShow(tvShow)
    }

    fun getAllTvShow(): DataSource.Factory<Int, TvShowModel> = mFilmDao.getAllTvShow()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowModel> = mFilmDao.getFavoriteTvShow()

    fun getDetailTvShowById(tvShowId: String): LiveData<TvShowModel> =
        mFilmDao.getDetailWithTvId(tvShowId)


    fun insertTvShowList(tvShow: List<TvShowModel>) = mFilmDao.insertTvShow(tvShow)

}