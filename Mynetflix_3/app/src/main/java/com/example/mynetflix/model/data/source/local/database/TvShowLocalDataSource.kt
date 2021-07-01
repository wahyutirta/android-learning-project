package com.example.mynetflix.model.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.mynetflix.model.data.TvShowModel

class TvShowLocalDataSource private constructor(private val managerTvShowDao: TvShowDao) {

    companion object {
        private var INSTANCE: TvShowLocalDataSource? = null

        fun getInstance(tvShowDao: TvShowDao): TvShowLocalDataSource =
            INSTANCE ?: TvShowLocalDataSource(tvShowDao).apply {
                INSTANCE = this
            }
    }

    fun getAllTvShow(): DataSource.Factory<Int, TvShowModel> = managerTvShowDao.getAllTvShow()

    fun getDetailTvShowById(tvShowId: String): LiveData<TvShowModel> =
        managerTvShowDao.getDetailWithTvId(tvShowId)

    fun insertTvShowList(tvShow: List<TvShowModel>) = managerTvShowDao.insertTvShow(tvShow)

    fun setTvShowFavorite(tvShow: TvShowModel, NewState: Boolean) {
        tvShow.favorite = NewState
        managerTvShowDao.updateTvShow(tvShow)
    }

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowModel> = managerTvShowDao.getFavoriteTvShow()

}