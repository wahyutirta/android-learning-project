package com.example.mynetflix.model.data.source.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.vo.Resource


interface FilmDataSource {


    fun setTvShowFavorite(tvShow: TvShowModel, NewState: Boolean)

    fun setMovieFavorite(movie: MovieModel, NewState: Boolean)

}