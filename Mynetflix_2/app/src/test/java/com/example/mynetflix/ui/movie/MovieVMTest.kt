package com.example.mynetflix.ui.movie

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieVMTest {

    private lateinit var viewModel: MovieVM

    @Before
    fun setUp() {
        viewModel = MovieVM()
    }


    @Test
    fun getMovieNotNull() {
        val movieModels = viewModel.getMovie()
        assertNotNull(movieModels)
    }

    @Test
    fun getMovie() {
        val movieModels = viewModel.getMovie()
        assertEquals(11, movieModels.size)
    }

}