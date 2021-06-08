package com.example.mynetflix.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.source.DataRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieVMTest{

    private lateinit var viewModel: MovieVM
    var dataMovies = DummyDataHelper.generateDataMovie()
    var movies = MutableLiveData<List<MovieModel>>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<MovieModel>>

    @Before
    fun setUp() {
        viewModel = MovieVM(dataRepository)
        movies.value = dataMovies
    }

    @Test
    fun getMovieNotNull() {

        Mockito.`when`(dataRepository.getAllMovies()).thenReturn(movies)
        val movieModels = viewModel.getMovie().value
        Mockito.verify(dataRepository).getAllMovies()
        assertNotNull(movieModels)

        viewModel.getMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dataMovies)
    }

    @Test
    fun getMovie() {

        Mockito.`when`(dataRepository.getAllMovies()).thenReturn(movies)
        val movieModels = viewModel.getMovie().value
        Mockito.verify(dataRepository).getAllMovies()
        assertNotNull(movieModels)
        assertEquals(11, movieModels?.size)

        viewModel.getMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dataMovies)
    }
}