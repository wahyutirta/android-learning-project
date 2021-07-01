package com.example.mynetflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.data.MovieModel

import com.example.mynetflix.model.data.source.remote.repository.MovieRepository
import com.example.mynetflix.vo.Resource

class DetailMovieVM(private val movieRepository: MovieRepository) : ViewModel() {

    val id = MutableLiveData<String>()


    fun setSelectedMovie(movieId: String) {
        this.id.value = movieId
    }

    var movieData: LiveData<Resource<MovieModel>> =
        Transformations.switchMap(id) { mMovieId ->
            movieRepository.getMoviesDetail(mMovieId)
        }

    fun favoriteHandler() {
        if (movieData.value?.data != null) {
            val resourceMovie = movieData.value
            val status = resourceMovie?.data?.favorite!!
            val detailMovie = resourceMovie?.data!!
            movieRepository.setMovieFavoriteState(detailMovie, !status)
        }
    }


}