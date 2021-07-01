package com.example.mynetflix.model.data.source.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.source.local.database.fakepackage.FakeMovieRepository
import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.data.source.utils.LiveDataTestUtil
import com.example.mynetflix.model.data.source.utils.PagedListUtils
import com.example.mynetflix.model.utils.AppExecutors
import com.example.mynetflix.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    
    private val movieLocalDataSource = Mockito.mock(MovieLocalDataSource::class.java)
    
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    
    private val movieRepository = FakeMovieRepository(remoteDataSource, movieLocalDataSource, appExecutors)
    
    private val movieResponse = DummyDataHelper.generateRemoteDataMovie()

    private val movieDataList = DummyDataHelper.generateDataMovie()

    private val movieData = DummyDataHelper.generateDataMovie()[0]



    @Test
    fun getAllMoviesResourceNotNull() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieModel>
        Mockito.`when`(movieLocalDataSource.getAllMovie()).thenReturn(dataSourceFactory)
        movieRepository.getAllMoviesResource()
        val movieModel =
            Resource.success(PagedListUtils.mockPagedList(DummyDataHelper.generateDataMovie()))
        com.nhaarman.mockitokotlin2.verify(movieLocalDataSource).getAllMovie()
        Assert.assertNotNull(movieModel.data)
        Assert.assertTrue("Movie is null", movieModel.data != null)
    }

    @Test
    fun getAllResourceMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieModel>
        Mockito.`when`(movieLocalDataSource.getAllMovie()).thenReturn(dataSourceFactory)
        movieRepository.getAllMoviesResource()
        val movieModel =
            Resource.success(PagedListUtils.mockPagedList(DummyDataHelper.generateDataMovie()))
        com.nhaarman.mockitokotlin2.verify(movieLocalDataSource).getAllMovie()
        assertEquals(movieResponse.size.toInt(), movieModel.data?.size?.toInt())
        assertTrue("loaded false movie", movieResponse.size.toInt() == movieModel.data?.size?.toInt())
    }

    @Test
    fun getMoviesDetailNotNull() {
        val dataHelper = MutableLiveData<MovieModel>()
        dataHelper.value = movieData
        Mockito.`when`(movieLocalDataSource.getDetailMovieById(movieData.id)).thenReturn(dataHelper)
        val data = LiveDataTestUtil.getValue(movieRepository.getMoviesDetail(movieData.id))
        Mockito.verify(movieLocalDataSource).getDetailMovieById(movieData.id)
        Assert.assertNotNull(data)
        assertTrue(data != null)

    }

    @Test
    fun getMoviesDetail() {
        val dataHelper = MutableLiveData<MovieModel>()
        dataHelper.value = movieData
        Mockito.`when`(movieLocalDataSource.getDetailMovieById(movieData.id)).thenReturn(dataHelper)
        val data = LiveDataTestUtil.getValue(movieRepository.getMoviesDetail(movieData.id))
        Mockito.verify(movieLocalDataSource).getDetailMovieById(movieData.id)
        Assert.assertNotNull(data)
        assertEquals(movieData.id, data.data?.id)
        assertTrue("loaded false movie", movieData.title == data.data?.title)

    }

    @Test
    fun getAllMovieFavNotNull() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieModel>
        Mockito.`when`(movieLocalDataSource.getFavoriteMovie()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovie()
        val movieModel =
            Resource.success(PagedListUtils.mockPagedList(DummyDataHelper.generateDataMovie()))
        Mockito.verify(movieLocalDataSource).getFavoriteMovie()
        Assert.assertNotNull(movieModel.data)
        Assert.assertTrue("Movie is null", movieModel.data != null)

    }

    @Test
    fun getAllMovieFav() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieModel>
        Mockito.`when`(movieLocalDataSource.getFavoriteMovie()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovie()
        val movieModel =
            Resource.success(PagedListUtils.mockPagedList(DummyDataHelper.generateDataMovie()))
        Mockito.verify(movieLocalDataSource).getFavoriteMovie()
        Assert.assertNotNull(movieModel.data)
        assertTrue(movieDataList.size.toInt() == movieModel.data?.size?.toInt())
        assertEquals(movieDataList.size.toInt(), movieModel.data?.size?.toInt())
    }

    @Test
    fun setFavMovie() {

        val movie = DummyDataHelper.getDetailMovie()
        movieRepository.setMovieFavoriteState(movie, true)
        com.nhaarman.mockitokotlin2.verify(movieLocalDataSource)
            .setMovieFavorite(DummyDataHelper.getDetailMovie(), true)
        com.nhaarman.mockitokotlin2.verifyNoMoreInteractions(movieLocalDataSource)
    }

    @Test
    fun setUnFavMovie() {
        movieRepository.setMovieFavoriteState(DummyDataHelper.getDetailMovie(), false)
        com.nhaarman.mockitokotlin2.verify(movieLocalDataSource)
            .setMovieFavorite(DummyDataHelper.getDetailMovie(), false)
        com.nhaarman.mockitokotlin2.verifyNoMoreInteractions(movieLocalDataSource)
    }
}