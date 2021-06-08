package com.example.mynetflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.source.DataRepository
import com.example.mynetflix.vo.Resource

class DetailMovieVM(private val dataRepository: DataRepository) : ViewModel() {

    val movieId = MutableLiveData<String>()

    fun setSelectedMovie(movieId: String) {
        this.movieId.value = movieId
    }

    var movieData: LiveData<Resource<MovieModel>> =
        Transformations.switchMap(movieId) { mMovieId ->
            dataRepository.getMoviesDetail(mMovieId)
        }

    fun getDetailMovieForTest(movieId: String): LiveData<MovieModel> =
        dataRepository.getDetailMovieById(movieId)

    fun setFavorite() {
        val movieResource = movieData.value
        if (movieResource != null) {
            val detailMovie = movieResource.data

            if (detailMovie != null) {
                val newState = !detailMovie.favorite
                dataRepository.setMovieFavorite(detailMovie, newState)
            }
        }
    }

    fun unFavorite() {
        val movieResource = movieData.value
        if (movieResource != null) {
            val detailMovie = movieResource.data

            if (detailMovie != null) {
                val newState = detailMovie.favorite
                dataRepository.setMovieFavorite(detailMovie, newState)
            }
        }
    }


}