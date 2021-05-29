package com.example.mynetflix.ui.detail

import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.TvShowModel
import com.example.mynetflix.model.DummyDataHelper

class DetailTvShowVM : ViewModel() {

    private lateinit var tvShowId: String

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getSelectedTvShow(): TvShowModel {
        lateinit var tvShow: TvShowModel
        val tvShowModels = DummyDataHelper.generateDataTvShow()
        tvShowModels
            .filter { tvShowId == it.id }
            .forEach { tvShow = it }
        return tvShow
    }

}