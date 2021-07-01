package com.example.mynetflix.ui.main.favorite.favtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mynetflix.model.data.TvShowModel

import com.example.mynetflix.model.data.source.remote.repository.TvShowRepository

class FavTvShowFragmentVM (private val tvShowRepository: TvShowRepository) : ViewModel() {

    fun getFavTvShow(): LiveData<PagedList<TvShowModel>> {
        return tvShowRepository.getFavoriteTvShow()
    }
}