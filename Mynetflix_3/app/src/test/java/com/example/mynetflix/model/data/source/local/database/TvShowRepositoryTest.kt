package com.example.mynetflix.model.data.source.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.local.database.fakepackage.FakeTvShowRepository
import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.data.source.utils.PagedListUtils
import com.example.mynetflix.model.utils.AppExecutors
import com.example.mynetflix.model.utils.LiveDataTestUtil
import com.example.mynetflix.vo.Resource
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TvShowRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)

    private val tvShowLocalDataSource = Mockito.mock(TvShowLocalDataSource::class.java)

    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val tvShowRepository = FakeTvShowRepository(remoteDataSource, tvShowLocalDataSource, appExecutors)

    private val tvShowResponse = DummyDataHelper.generateRemoteDataTvShow()

    private val tvShowDataList = DummyDataHelper.generateDataTvShow()

    private val tvShowData = DummyDataHelper.generateDataTvShow()[0]


    @Test
    fun getAllTvShowResourceNotNull() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowModel>
        Mockito.`when`(tvShowLocalDataSource.getAllTvShow()).thenReturn(dataSourceFactory)
        tvShowRepository.getAllTvShowResource()
        val tvShowModel =
            Resource.success(PagedListUtils.mockPagedList(DummyDataHelper.generateDataTvShow()))
        com.nhaarman.mockitokotlin2.verify(tvShowLocalDataSource).getAllTvShow()
        assertNotNull(tvShowModel.data)
        assertTrue("TvShow is null", tvShowModel.data != null)
    }

    @Test
    fun getAllTvShowResource() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowModel>
        Mockito.`when`(tvShowLocalDataSource.getAllTvShow()).thenReturn(dataSourceFactory)
        tvShowRepository.getAllTvShowResource()
        val tvShowModel =
            Resource.success(PagedListUtils.mockPagedList(DummyDataHelper.generateDataTvShow()))
        com.nhaarman.mockitokotlin2.verify(tvShowLocalDataSource).getAllTvShow()
        assertEquals(tvShowResponse.size.toInt(), tvShowModel.data?.size?.toInt())
        assertTrue(
            "loaded false movie",
            tvShowResponse.size.toInt() == tvShowModel.data?.size?.toInt())

    }

    @Test
    fun getTvShowDetailNotNull() {
        val dataHelper = MutableLiveData<TvShowModel>()
        dataHelper.value = tvShowData
        Mockito.`when`(tvShowLocalDataSource.getDetailTvShowById(tvShowData.id)).thenReturn(dataHelper)
        val data = LiveDataTestUtil.getValue(tvShowRepository.getTvShowDetail(tvShowData.id))
        Mockito.verify(tvShowLocalDataSource).getDetailTvShowById(tvShowData.id)
        assertNotNull(data)
        assertTrue(data != null)
    }

    @Test
    fun getTvShowDetail() {
        val dataHelper = MutableLiveData<TvShowModel>()
        dataHelper.value = tvShowData
        Mockito.`when`(tvShowLocalDataSource.getDetailTvShowById(tvShowData.id)).thenReturn(dataHelper)
        val data = LiveDataTestUtil.getValue(tvShowRepository.getTvShowDetail(tvShowData.id))
        Mockito.verify(tvShowLocalDataSource).getDetailTvShowById(tvShowData.id)
        assertNotNull(data)
        assertEquals(tvShowData.id, data.data?.id)
        assertTrue("loaded false tvshow",tvShowData.title == data.data?.title )
    }

    @Test
    fun getAllTvShowFavNotNull() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowModel>
        Mockito.`when`(tvShowLocalDataSource.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        tvShowRepository.getFavoriteTvShow()
        val tvShowModel =
            Resource.success(PagedListUtils.mockPagedList(DummyDataHelper.generateDataTvShow()))
        Mockito.verify(tvShowLocalDataSource).getFavoriteTvShow()
        assertNotNull(tvShowModel.data)
        assertTrue("tvShow is null", tvShowModel.data != null)

    }

    @Test
    fun getAllTvShowFav() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowModel>
        Mockito.`when`(tvShowLocalDataSource.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        tvShowRepository.getFavoriteTvShow()
        val tvShowModel =
            Resource.success(PagedListUtils.mockPagedList(DummyDataHelper.generateDataTvShow()))
        Mockito.verify(tvShowLocalDataSource).getFavoriteTvShow()
        assertTrue(tvShowDataList.size.toInt() == tvShowModel.data?.size?.toInt())
        assertEquals(tvShowDataList.size.toInt(), tvShowModel.data?.size?.toInt())
    }


    @Test
    fun setFavoriteTvShow() {
        tvShowRepository.setTvShowFavoriteState(DummyDataHelper.getDetailTvShow(), true)
        com.nhaarman.mockitokotlin2.verify(tvShowLocalDataSource).setTvShowFavorite(DummyDataHelper.getDetailTvShow(), true)
       com.nhaarman.mockitokotlin2.verifyNoMoreInteractions(tvShowLocalDataSource)
    }

    @Test
    fun unFavoriteTvShow() {
        tvShowRepository.setTvShowFavoriteState(DummyDataHelper.getDetailTvShow(), false)
        com.nhaarman.mockitokotlin2.verify(tvShowLocalDataSource).setTvShowFavorite(DummyDataHelper.getDetailTvShow(), false)
        com.nhaarman.mockitokotlin2.verifyNoMoreInteractions(tvShowLocalDataSource)
    }
}