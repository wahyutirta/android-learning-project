package com.example.mynetflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.source.DataRepository

class DetailMovieVM(private val dataRepository: DataRepository) : ViewModel() {

    private lateinit var movieId: String
    fun getMovie(): LiveData<MovieModel> = dataRepository.getMoviesDetail(movieId)
    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }


}