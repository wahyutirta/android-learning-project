package com.example.mynetflix.ui.main.favorite.favmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.source.remote.repository.MovieRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavMovieFragmentVMTest{

    private lateinit var viewModel: FavMovieFragmentVM
    private var  movieModel : PagedList<MovieModel>? = null
    private var movie = MutableLiveData<PagedList<MovieModel>>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var moviePagedList: PagedList<MovieModel>

    @Mock
    private lateinit var observer: Observer<PagedList<MovieModel>>

    @Before
    fun setUp() {
        viewModel = FavMovieFragmentVM(movieRepository)
    }


    @Test
    fun getFavoriteListMovie() {

        val dataMovie = moviePagedList
        Mockito.`when`(dataMovie.size).thenReturn(10)

        movie.value = dataMovie

        Mockito.`when`(movieRepository.getFavoriteMovie()).thenReturn(movie)

        movieModel = viewModel.getFavMovies().value
        Mockito.verify(movieRepository).getFavoriteMovie()
        assertNotNull(movieModel)
        assertNotSame(null,movieModel)
        assertTrue(movieModel?.size == 10)
        assertEquals(10, movieModel?.size)


        viewModel.getFavMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dataMovie)
    }

}