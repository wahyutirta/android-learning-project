package com.example.mynetflix.model.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.vo.Resource


interface TvShowDataSourceInterface {

    fun getAllTvShowResource(): LiveData<Resource<PagedList<TvShowModel>>>

    fun getAllTvShow(): LiveData<PagedList<TvShowModel>>

    fun getTvShowDetail(tvShowId: String): LiveData<Resource<TvShowModel>>

    fun getDetailTvShowById(tvShowId: String): LiveData<TvShowModel>

    fun setTvShowFavoriteState(tvShow: TvShowModel, NewState: Boolean)

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowModel>>

}