package com.example.mynetflix.ui.detail

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
class DetailMovieVMTest {
    private lateinit var viewModel: DetailMovieVM
    private val movieId = DummyDataHelper.generateDataMovie()[0].id
    private val dataHelper = DummyDataHelper.generateDataMovie()[0]
    var movie = MutableLiveData<MovieModel>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieModel>

    @Before
    fun setUp() {
        viewModel = DetailMovieVM(dataRepository)
        viewModel.setSelectedMovie(movieId)
        movie.value = dataHelper
    }

    @Test
    fun getMovieNotNull() {

        Mockito.`when`(dataRepository.getMoviesDetail(movieId)).thenReturn(movie)
        var movieModel = viewModel.getMovie().value as MovieModel
        Mockito.verify(dataRepository).getMoviesDetail(movieId)
        assertNotNull(movieModel)
        viewModel.getMovie().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dataHelper)
    }

    @Test
    fun getMovie() {

        Mockito.`when`(dataRepository.getMoviesDetail(movieId)).thenReturn(movie)
        var movieModel = viewModel.getMovie().value as MovieModel
        Mockito.verify(dataRepository).getMoviesDetail(movieId)

        assertEquals(dataHelper.id, movieModel.id)
        assertEquals(dataHelper.title, movieModel.title)
        assertEquals(dataHelper.releaseDate, movieModel.releaseDate)
        assertEquals(dataHelper.movieRate, movieModel.movieRate)
        assertEquals(dataHelper.description, movieModel.description)
        assertEquals(dataHelper.genres, movieModel.genres)
        assertEquals(dataHelper.originalLanguage, movieModel.originalLanguage)
        assertEquals(dataHelper.imagePath, movieModel.imagePath)
        assertEquals(dataHelper.filmDirector, movieModel.filmDirector)

        viewModel.getMovie().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dataHelper)
    }


}