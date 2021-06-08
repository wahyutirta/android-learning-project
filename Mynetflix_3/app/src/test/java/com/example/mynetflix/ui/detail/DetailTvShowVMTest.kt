package com.example.mynetflix.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.TvShowModel
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
class DetailTvShowVMTest {

    private lateinit var viewModel: DetailTvShowVM
    private val dataHelper = DummyDataHelper.generateDataTvShow()[0]
    private val tvShowId = DummyDataHelper.generateDataTvShow()[0].id
    var tvShow = MutableLiveData<TvShowModel>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: DataRepository

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowModel>

    @Before
    fun setUp() {
        viewModel = DetailTvShowVM(filmRepository)
        viewModel.setSelectedTvShow(tvShowId)
        tvShow.value = dataHelper
    }

    @Test
    fun getTvShowNotNull() {


        Mockito.`when`(filmRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        var tvShowModel = viewModel.getTvShow().value as TvShowModel
        Mockito.verify(filmRepository).getTvShowDetail(tvShowId)

        assertNotNull(tvShowModel)
        viewModel.getTvShow().observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(dataHelper)
    }

    @Test
    fun getTvShow() {


        Mockito.`when`(filmRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        var tvShowModel = viewModel.getTvShow().value as TvShowModel
        Mockito.verify(filmRepository).getTvShowDetail(tvShowId)

        assertEquals(dataHelper.id, tvShowModel.id)
        assertEquals(dataHelper.title, tvShowModel.title)
        assertEquals(dataHelper.releaseDate, tvShowModel.releaseDate)
        assertEquals(dataHelper.ratings, tvShowModel.ratings)
        assertEquals(dataHelper.description, tvShowModel.description)
        assertEquals(dataHelper.tvShowGenre, tvShowModel.tvShowGenre)
        assertEquals(dataHelper.originalLanguage, tvShowModel.originalLanguage)
        assertEquals(dataHelper.numOfEpisodes, tvShowModel.numOfEpisodes)
        assertEquals(dataHelper.numOfSeasons, tvShowModel.numOfSeasons)
        assertEquals(dataHelper.runTimes, tvShowModel.runTimes)
        assertEquals(dataHelper.imagePath, tvShowModel.imagePath)
        assertEquals(dataHelper.creators, tvShowModel.creators)

        viewModel.getTvShow().observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(dataHelper)
    }
}