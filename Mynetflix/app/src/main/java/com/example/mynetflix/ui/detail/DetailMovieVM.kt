package com.example.mynetflix.ui.detail

import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.MovieModel
import com.example.mynetflix.model.DummyDataHelper

class DetailMovieVM : ViewModel(){
    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getSelectedMovie(): MovieModel {
        lateinit var movie: MovieModel
        val models = DummyDataHelper.generateDataMovie()
        models
            .filter { movieId == it.id }
            .forEach { movie = it }
        return movie
    }
}