package com.example.mynetflix.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.source.DataRepository
import com.example.mynetflix.vo.Resource

class TvShowVM (private val dataRepository: DataRepository) : ViewModel() {

    fun getTvShow(): LiveData<Resource<PagedList<TvShowModel>>> = dataRepository.getAllTvShow()

}