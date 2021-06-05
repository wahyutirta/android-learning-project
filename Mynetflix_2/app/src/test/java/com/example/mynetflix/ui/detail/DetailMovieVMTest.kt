package com.example.mynetflix.ui.detail

import com.example.mynetflix.model.data.DummyDataHelper
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailMovieVMTest {
    private lateinit var viewModel: DetailMovieVM
    private val dummyData = DummyDataHelper.generateDataMovie()[0]
    private val movieId = dummyData.id

    @Before
    fun setUp() {
        viewModel = DetailMovieVM()
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovieNotNull() {
        viewModel.setSelectedMovie(dummyData.id)
        val movieModel = viewModel.getSelectedMovie()
        assertNotNull(movieModel)
    }



    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyData.id)
        val movieModel = viewModel.getSelectedMovie()
        assertEquals(dummyData.id, movieModel.id)
        assertEquals(dummyData.title, movieModel.title)
        assertEquals(dummyData.releaseDate, movieModel.releaseDate)
        assertEquals(dummyData.movieRate, movieModel.movieRate)
        assertEquals(dummyData.description, movieModel.description)
        assertEquals(dummyData.genres, movieModel.genres)
        assertEquals(dummyData.originalLanguage, movieModel.originalLanguage)
        assertEquals(dummyData.imagePath, movieModel.imagePath)
        assertEquals(dummyData.filmDirector, movieModel.filmDirector)
        assertNotNull(movieModel)
    }



}