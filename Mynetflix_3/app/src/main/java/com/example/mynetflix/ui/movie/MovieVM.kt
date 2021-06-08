package com.example.mynetflix.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.source.DataRepository
import com.example.mynetflix.model.data.source.remote.response.MovieResponse
import com.example.mynetflix.vo.Resource

class MovieVM (private val dataRepository: DataRepository) : ViewModel() {

    fun getMovie(): LiveData<Resource<PagedList<MovieModel>>> = dataRepository.getAllMovies()
}