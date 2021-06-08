package com.example.mynetflix.ui.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.utils.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DataRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val dataRepository = FakeRepository(remote)


    private val movieDResponse = DummyDataHelper.generateRemoteDataMovie()
    private val movieId = movieDResponse[0].id

    private val tvShowDResponse = DummyDataHelper.generateRemoteDataTvShow()
    private val tvShowId = tvShowDResponse[0].id



    @Test
    fun getTvshowNotNull() {
        //tv show
        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowDResponse)
        }.`when`(remote).getAllTvShow(com.nhaarman.mockitokotlin2.any())

        val allTvShow = LiveDataTestUtil.getValue(dataRepository.getAllTvShow())

        Mockito.verify(remote).getAllTvShow(com.nhaarman.mockitokotlin2.any())
        assertNotNull(allTvShow)


    }

    @Test
    fun getDetailTvShowNotNull() {
        //detail
        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowDResponse)
        }.`when`(remote).getAllTvShow(com.nhaarman.mockitokotlin2.any())

        val detailTvShow = LiveDataTestUtil.getValue(dataRepository.getTvShowDetail(tvShowId))

        Mockito.verify(remote).getAllTvShow(com.nhaarman.mockitokotlin2.any())
        assertNotNull(detailTvShow)
        assertNotNull(detailTvShow.title)
    }

    @Test
    fun getDetailMovieNotNull() {
        //detail
        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMovieReceived(movieDResponse)
        }.`when`(remote).getAllMovies(com.nhaarman.mockitokotlin2.any())

        val detailMovie = LiveDataTestUtil.getValue(dataRepository.getMoviesDetail(movieId))

        Mockito.verify(remote).getAllMovies(com.nhaarman.mockitokotlin2.any())
        assertNotNull(detailMovie)
        assertNotNull(detailMovie.title)
    }

    @Test
    fun getMovieNotNull() {
        //movie
        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMovieReceived(movieDResponse)
        }.`when`(remote).getAllMovies(com.nhaarman.mockitokotlin2.any())

        val allMovie = LiveDataTestUtil.getValue(dataRepository.getAllMovies())

        Mockito.verify(remote).getAllMovies(com.nhaarman.mockitokotlin2.any())
        assertNotNull(allMovie)

    }



    @Test
    fun getAllMovies() {
        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMovieReceived(movieDResponse)
        }.`when`(remote).getAllMovies(com.nhaarman.mockitokotlin2.any())

        val movieEntities = LiveDataTestUtil.getValue(dataRepository.getAllMovies())

        Mockito.verify(remote).getAllMovies(com.nhaarman.mockitokotlin2.any())

        assertEquals(movieDResponse.size.toLong(), movieEntities.size.toLong())

    }

    @Test
    fun getAllTvShow() {
        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowDResponse)
        }.`when`(remote).getAllTvShow(com.nhaarman.mockitokotlin2.any())
        val tvShowEntities = LiveDataTestUtil.getValue(dataRepository.getAllTvShow())
        Mockito.verify(remote).getAllTvShow(com.nhaarman.mockitokotlin2.any())

        assertEquals(tvShowDResponse.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getMoviesDetail() {
        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMovieReceived(movieDResponse)
            null
        }.`when`(remote).getAllMovies(com.nhaarman.mockitokotlin2.any())
        val movieEntities = LiveDataTestUtil.getValue(dataRepository.getMoviesDetail(movieId))
        Mockito.verify(remote).getAllMovies(com.nhaarman.mockitokotlin2.any())

        assertEquals(movieDResponse[0].title, movieEntities.title)



    }

    @Test
    fun getTvShowDetail() {
        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowDResponse)
        }.`when`(remote).getAllTvShow(com.nhaarman.mockitokotlin2.any())
        val tvShowEntities = LiveDataTestUtil.getValue(dataRepository.getTvShowDetail(tvShowId))
        Mockito.verify(remote).getAllTvShow(com.nhaarman.mockitokotlin2.any())

        assertEquals(tvShowDResponse[0].title, tvShowEntities.title)

    }
}