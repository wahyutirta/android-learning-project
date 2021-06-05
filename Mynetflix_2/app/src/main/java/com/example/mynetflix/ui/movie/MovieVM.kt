package com.example.mynetflix.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.source.DataRepository

class MovieVM (private val dataRepository: DataRepository) : ViewModel() {

    fun getMovie(): LiveData<List<MovieModel>> = dataRepository.getAllMovies()
}