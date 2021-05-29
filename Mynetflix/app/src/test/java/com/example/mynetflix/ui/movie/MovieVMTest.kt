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
    fun getMovie() {
        val movieModels = viewModel.getMovie()
        assertNotNull(movieModels)
        assertEquals(11, movieModels.size)
    }

}