package com.example.mynetflix.ui.movie

import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.MovieModel
import com.example.mynetflix.model.DummyDataHelper

class MovieVM : ViewModel() {

    fun getMovie(): List<MovieModel> = DummyDataHelper.generateDataMovie()

}