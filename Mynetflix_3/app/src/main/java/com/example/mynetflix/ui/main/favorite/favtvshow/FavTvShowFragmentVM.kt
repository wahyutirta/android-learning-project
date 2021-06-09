package com.example.mynetflix.ui.main.favorite.favtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.DataRepository

class FavTvShowFragmentVM (private val dataRepository: DataRepository) : ViewModel() {

    fun getFavTvShow(): LiveData<PagedList<TvShowModel>> {
        return dataRepository.getFavoriteTvShow()
    }
}