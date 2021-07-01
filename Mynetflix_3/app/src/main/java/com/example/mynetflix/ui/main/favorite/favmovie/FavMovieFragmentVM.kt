package com.example.mynetflix.ui.main.favorite.favmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.source.remote.repository.MovieRepository


class FavMovieFragmentVM (private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavMovies(): LiveData<PagedList<MovieModel>> {
        return movieRepository.getFavoriteMovie()
    }

}