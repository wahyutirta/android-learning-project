package com.example.mynetflix.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.source.remote.repository.MovieRepository
import com.example.mynetflix.vo.Resource
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MovieVMTest{

    private lateinit var viewModel: MovieVM
    private var actualValueDataSize: Int? = null
    private var actualValue : Resource<PagedList<MovieModel>>? = null
    private var expectedValue : Resource<PagedList<MovieModel>>? = null
    private var actualMessage : String? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieModel>>>

    @Before
    fun setUp() {
        viewModel = MovieVM(movieRepository)
    }

    @Test
    fun getMovieSuccess() {
        val movies = PagedTestDataSources.snapshot(DummyDataHelper.generateDataMovie())
        val expected = MutableLiveData<Resource<PagedList<MovieModel>>>()
        expected.value = Resource.success(movies)

        Mockito.`when`(movieRepository.getAllMoviesResource()).thenReturn(expected)

        viewModel.getMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        expectedValue = expected.value
        actualValue = viewModel.getMovie().value

        assertNotNull(actualValue)
        assertNotSame(null,actualValue)
        assertSame("all movie loaded", expectedValue?.data?.size, actualValue?.data?.size)
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun getMovieSuccessEmpty() {
        val movie = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<MovieModel>>>()
        expected.value = Resource.success(movie)

        Mockito.`when`(movieRepository.getAllMoviesResource()).thenReturn(expected)

        viewModel.getMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)


        actualValueDataSize = viewModel.getMovie().value?.data?.size
        val tempData = viewModel.getMovie().value?.data
        assertFalse("movie data size more than 0", actualValueDataSize!! > 0)
        assertTrue("movie data size is 0 is not true", tempData?.size == 0)

        assertEquals(0, actualValueDataSize)
        assertSame(0, actualValueDataSize)
        Assert.assertTrue(
            "size data is 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )

    }

    @Test
    fun getMovieError() {
        val expectedMessage = "Something happen"
        val expected = MutableLiveData<Resource<PagedList<MovieModel>>>()
        expected.value = Resource.error(expectedMessage, null)

        Mockito.`when`(movieRepository.getAllMoviesResource()).thenReturn(expected)

        viewModel.getMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        actualMessage = viewModel.getMovie().value?.message
        assertNotNull(actualMessage)
        assertNotSame(null, actualMessage)
        assertTrue("message are same", actualMessage == expectedMessage)
        assertFalse("message are not same", actualMessage != expectedMessage)

        assertEquals(expectedMessage, actualMessage)
        assertSame(expectedMessage, actualMessage)

    }

    class PagedTestDataSources private constructor(private val items: List<MovieModel>) : PositionalDataSource<MovieModel>() {
        companion object {
            fun snapshot(items: List<MovieModel> = listOf()): PagedList<MovieModel> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieModel>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieModel>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}