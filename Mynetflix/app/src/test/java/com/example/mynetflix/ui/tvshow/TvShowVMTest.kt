package com.example.mynetflix.ui.tvshow

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowVMTest{

    private lateinit var viewModel: TvShowVM

    @Before
    fun setUp() {
        viewModel = TvShowVM()
    }

    @Test
    fun getTvShow() {
        val tvShowModels = viewModel.getTvShow()
        assertNotNull(tvShowModels)
        assertEquals(10, tvShowModels.size)
    }
}