package com.example.mynetflix.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.TvShowModel
import com.example.mynetflix.model.DummyDataHelper

class TvShowVM : ViewModel() {
    fun getTvShow(): List<TvShowModel> = DummyDataHelper.generateDataTvShow()

}