package com.example.mynetflix.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.DataRepository

class TvShowVM (private val dataRepository: DataRepository) : ViewModel() {

    fun getTvShow(): LiveData<List<TvShowModel>> = dataRepository.getTvShow()

}