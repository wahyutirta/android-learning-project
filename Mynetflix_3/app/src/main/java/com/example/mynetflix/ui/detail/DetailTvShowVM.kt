package com.example.mynetflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.remote.repository.TvShowRepository

import com.example.mynetflix.vo.Resource

class DetailTvShowVM(private val tvShowRepository: TvShowRepository) : ViewModel() {

    val id = MutableLiveData<String>()

    fun setSelectedTvShow(tvShowId: String) {
        this.id.value = tvShowId
    }

    var tvData: LiveData<Resource<TvShowModel>> =
        Transformations.switchMap(id) { mTvShowId ->
            tvShowRepository.getTvShowDetail(mTvShowId)
        }

    fun favoriteHandler() {
        if (tvData.value?.data != null) {
            val resourceTv = tvData.value
            val status = resourceTv?.data?.favorite!!
            val detailTvShow = resourceTv?.data!!
            tvShowRepository.setTvShowFavoriteState(detailTvShow, !status)
        }
    }


}