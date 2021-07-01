package com.example.mynetflix.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.remote.repository.TvShowRepository
import com.example.mynetflix.vo.Resource

class TvShowVM (private val tvShowRepository: TvShowRepository) : ViewModel() {

    fun getTvShow(): LiveData<Resource<PagedList<TvShowModel>>> = tvShowRepository.getAllTvShowResource()

}