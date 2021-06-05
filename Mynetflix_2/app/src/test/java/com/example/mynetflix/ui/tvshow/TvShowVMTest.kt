package com.example.mynetflix.ui.tvshow

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
class TvShowVMTest {

    private lateinit var viewModel: TvShowVM
    var tvShow = MutableLiveData<List<TvShowModel>>()
    var dataTvShow = DummyDataHelper.generateDataTvShow()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowModel>>

    @Before
    fun setUp() {
        viewModel = TvShowVM(dataRepository)
        tvShow.value = dataTvShow
    }

    @Test
    fun getTvShowNotNull() {

        Mockito.`when`(dataRepository.getAllTvShow()).thenReturn(tvShow)
        val tvShowModels = viewModel.getTvShow().value
        Mockito.verify(dataRepository).getAllTvShow()
        assertNotNull(tvShowModels)


        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dataTvShow)
    }

    @Test
    fun getTvShow() {

        Mockito.`when`(dataRepository.getAllTvShow()).thenReturn(tvShow)
        val tvShowModels = viewModel.getTvShow().value
        Mockito.verify(dataRepository).getAllTvShow()
        assertEquals(10, tvShowModels?.size)

        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dataTvShow)
    }
}