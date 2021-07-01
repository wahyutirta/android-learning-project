package com.example.mynetflix.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.source.remote.repository.MovieRepository
import com.example.mynetflix.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieVMTest{

    private lateinit var viewModel: DetailMovieVM
    private var dataHelper = DummyDataHelper.generateDataMovie()[0]
    private var movieId = dataHelper.id
    private var movieData = MutableLiveData<MovieModel>()
    private var movieModelNullable : MovieModel? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieModel>>


    @Before
    fun setupMovie() {
        viewModel = DetailMovieVM(movieRepository)
        viewModel.setSelectedMovie(movieId)
    }


    @Test
    fun detailAndFavoriteHandlerMovie() {
        val movieExpectedData = MutableLiveData<Resource<MovieModel>>()
        val dummyDetailMovie = Resource.success(DummyDataHelper.getDetailMovie())

        movieExpectedData.value = dummyDetailMovie

        Mockito.`when`(movieRepository.getMoviesDetail(movieId)).thenReturn(movieExpectedData)

        //observe perubahan pada movieData
        viewModel.movieData.observeForever(movieObserver)
        //cek apakah data terload atau berubah
        verify(movieObserver).onChanged(movieExpectedData.value)
        verifyNoMoreInteractions(movieObserver)
        val expectedMovie = movieExpectedData.value
        val loadedValue = movieRepository.getMoviesDetail(movieId).value

        assertEquals(loadedValue, viewModel.movieData.value)
        assertEquals(expectedMovie, viewModel.movieData.value)
        assertEquals(expectedMovie, loadedValue)

        //set favorite
        viewModel.favoriteHandler()
        verify(movieObserver).onChanged(movieExpectedData.value)
        verifyNoMoreInteractions(movieObserver)

        assertEquals(loadedValue, viewModel.movieData.value)
        assertEquals(expectedMovie, viewModel.movieData.value)
        assertEquals(expectedMovie, loadedValue)

        //set unfavorite
        viewModel.favoriteHandler()
        verify(movieObserver).onChanged(movieExpectedData.value)
        verifyNoMoreInteractions(movieObserver)

        assertEquals(loadedValue, viewModel.movieData.value)
        assertEquals(expectedMovie, viewModel.movieData.value)
        assertEquals(expectedMovie, loadedValue)
    }

}