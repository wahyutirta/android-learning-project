package com.example.mynetflix.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.source.remote.repository.MovieRepository

import com.example.mynetflix.vo.Resource

class MovieVM (private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovie(): LiveData<Resource<PagedList<MovieModel>>> = movieRepository.getAllMoviesResource()
}