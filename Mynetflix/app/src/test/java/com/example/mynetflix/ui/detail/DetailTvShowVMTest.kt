package com.example.mynetflix.ui.detail

import com.example.mynetflix.model.DummyDataHelper
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailTvShowVMTest{

    private lateinit var viewModel: DetailTvShowVM
    private val dummyData = DummyDataHelper.generateDataTvShow()[0]
    private val tvShowId = dummyData.id

    @Before
    fun setUp() {
        viewModel = DetailTvShowVM()
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedTvShow(dummyData.id)
        val tvShowModel = viewModel.getSelectedTvShow()
        assertNotNull(tvShowModel)
        assertEquals(dummyData.id, tvShowModel.id)
        assertEquals(dummyData.title, tvShowModel.title)
        assertEquals(dummyData.releaseDate, tvShowModel.releaseDate)
        assertEquals(dummyData.ratings, tvShowModel.ratings)
        assertEquals(dummyData.description, tvShowModel.description)
        assertEquals(dummyData.tvShowGenre, tvShowModel.tvShowGenre)
        assertEquals(dummyData.originalLanguage, tvShowModel.originalLanguage)
        assertEquals(dummyData.numOfEpisodes, tvShowModel.numOfEpisodes)
        assertEquals(dummyData.numOfSeasons, tvShowModel.numOfSeasons)
        assertEquals(dummyData.runTimes, tvShowModel.runTimes)
        assertEquals(dummyData.imagePath, tvShowModel.imagePath)
        assertEquals(dummyData.creators, tvShowModel.creators)
    }

    @Test
    fun getSelectedTvShow(){
        val TvShowSelected = viewModel.getSelectedTvShow()
        assertNotNull(TvShowSelected)
    }
}