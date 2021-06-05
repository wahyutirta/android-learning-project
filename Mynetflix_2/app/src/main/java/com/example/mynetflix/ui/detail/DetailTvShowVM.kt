package com.example.mynetflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.source.DataRepository

class DetailTvShowVM(private val dataRepository: DataRepository) : ViewModel() {

    private lateinit var tvShowId: String
    fun getTvShow(): LiveData<TvShowModel> = dataRepository.getTvShowDetail(tvShowId)
    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }


}