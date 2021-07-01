package com.example.mynetflix.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.remote.repository.TvShowRepository
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
class DetailTvShowVMTest {

    private lateinit var viewModel: DetailTvShowVM
    private var dataHelper = DummyDataHelper.generateDataTvShow()[0]
    private var tvShowId = dataHelper.id
    private var tvShowDummy = MutableLiveData<TvShowModel>()
    private var tvShowModelNullable : TvShowModel? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowModel>>

    @Before
    fun setUpTvShow() {
        viewModel = DetailTvShowVM(tvShowRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }


    @Test
    fun detailAndFavoriteHandlerTvShow() {
        val tvShowExpectedData = MutableLiveData<Resource<TvShowModel>>()
        val dummyDetailTvShow = Resource.success(DummyDataHelper.getDetailTvShow())

        tvShowExpectedData.value = dummyDetailTvShow

        Mockito.`when`(tvShowRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowExpectedData)
        //observe perubahan pada tvData
        viewModel.tvData.observeForever(tvShowObserver)
        //cek apakah data terload atau berubah
        verify(tvShowObserver).onChanged(tvShowExpectedData.value)
        verifyNoMoreInteractions(tvShowObserver)
        val expectedTvShow = tvShowExpectedData.value
        val loadedValue = tvShowRepository.getTvShowDetail(tvShowId).value

        assertEquals(loadedValue , viewModel.tvData.value)
        assertEquals(expectedTvShow, viewModel.tvData.value)
        assertEquals(expectedTvShow, loadedValue)

        //set favorite
        viewModel.favoriteHandler()
        verify(tvShowObserver).onChanged(tvShowExpectedData.value)
        verifyNoMoreInteractions(tvShowObserver)

        assertEquals(expectedTvShow, viewModel.tvData.value)

        assertEquals(loadedValue , viewModel.tvData.value)
        assertEquals(expectedTvShow, viewModel.tvData.value)
        assertEquals(expectedTvShow, loadedValue)

        //set unfavorite
        viewModel.favoriteHandler()
        verify(tvShowObserver).onChanged(tvShowExpectedData.value)
        verifyNoMoreInteractions(tvShowObserver)

        assertEquals(loadedValue , viewModel.tvData.value)
        assertEquals(expectedTvShow, viewModel.tvData.value)
        assertEquals(expectedTvShow, loadedValue)

    }
}