package com.example.mynetflix.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.remote.repository.TvShowRepository
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
class TvShowVMTest{

    private lateinit var viewModel: TvShowVM
    private var actualValueDataSize: Int? = null
    private var actualValue :  Resource<PagedList<TvShowModel>>? = null
    private var expectedValue : Resource<PagedList<TvShowModel>>? = null
    private var actualMessage : String? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowModel>>>

    @Before
    fun setUp() {
        viewModel = TvShowVM(tvShowRepository)
    }

    @Test
    fun getTvShowSuccess() {
        val expected = MutableLiveData<Resource<PagedList<TvShowModel>>>()
        val tvShow = PagedTestDataSources.snapshot(DummyDataHelper.generateDataTvShow())

        expected.value = Resource.success(tvShow)

        Mockito.`when`(tvShowRepository.getAllTvShowResource()).thenReturn(expected)

        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        expectedValue = expected.value
        actualValue = viewModel.getTvShow().value

        assertNotNull(actualValue)
        assertNotSame(null,actualValue)
        assertSame("all tvshow loaded", expectedValue?.data?.size, actualValue?.data?.size)
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun getTvShowSuccessEmpty() {
        val tvShow = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TvShowModel>>>()
        expected.value = Resource.success(tvShow)

        Mockito.`when`(tvShowRepository.getAllTvShowResource()).thenReturn(expected)

        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        actualValueDataSize = viewModel.getTvShow().value?.data?.size
        val tempData = viewModel.getTvShow().value?.data
        assertFalse("tvshow data size more than 0", actualValueDataSize!! > 0)
        assertTrue("tvshow data size is 0 is not true", tempData?.size == 0)
        assertEquals(0, actualValueDataSize)
        assertSame(0, actualValueDataSize)
        Assert.assertTrue(
            "size data is 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )

    }

    @Test
    fun getTvShowError() {
        val expectedMessage = "Something happen"
        val expected = MutableLiveData<Resource<PagedList<TvShowModel>>>()
        expected.value = Resource.error(expectedMessage, null)

        Mockito.`when`(tvShowRepository.getAllTvShowResource()).thenReturn(expected)

        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        actualMessage = viewModel.getTvShow().value?.message
        assertNotNull(actualMessage)
        assertNotSame(null, actualMessage)
        assertTrue("message are same", actualMessage == expectedMessage)
        assertFalse("message are not same", actualMessage != expectedMessage)

        assertEquals(expectedMessage, actualMessage)
        assertSame(expectedMessage, actualMessage)

    }

    class PagedTestDataSources private constructor(private val items: List<TvShowModel>) : PositionalDataSource<TvShowModel>() {
        companion object {
            fun snapshot(items: List<TvShowModel> = listOf()): PagedList<TvShowModel> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvShowModel>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvShowModel>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}