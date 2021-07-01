package com.example.mynetflix.ui.main.favorite.favtvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.remote.repository.TvShowRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavTvShowFragmentVMTest{

    private lateinit var viewModel: FavTvShowFragmentVM
    private var  tvShowModel : PagedList<TvShowModel>? = null
    private var tvShow = MutableLiveData<PagedList<TvShowModel>>()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowModel>>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowModel>

    @Before
    fun setup() {
        viewModel = FavTvShowFragmentVM(tvShowRepository)
    }


    @Test
    fun getFavoriteListTvShow() {
        val dataTvShow = tvShowPagedList
        Mockito.`when`(dataTvShow.size).thenReturn(10)

        tvShow.value = dataTvShow

        Mockito.`when`(tvShowRepository.getFavoriteTvShow()).thenReturn(tvShow)

        tvShowModel = viewModel.getFavTvShow().value
        Mockito.verify(tvShowRepository).getFavoriteTvShow()
        assertNotNull(tvShowModel)
        assertNotSame(null,tvShowModel)
        assertTrue(tvShowModel?.size == 10)
        assertEquals(10, tvShowModel?.size)


        viewModel.getFavTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dataTvShow)
    }


}