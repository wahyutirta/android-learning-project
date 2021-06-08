package com.example.mynetflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.DataRepository
import com.example.mynetflix.vo.Resource

class DetailTvShowVM(private val dataRepository: DataRepository) : ViewModel() {

    val tvShowId = MutableLiveData<String>()

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId.value = tvShowId
    }

    var tvData: LiveData<Resource<TvShowModel>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            dataRepository.getTvShowDetail(mTvShowId)
        }

    fun getTvShowForTest(movieId: String): LiveData<TvShowModel> =
        dataRepository.getDetailTvShowById(movieId)

    fun setTvShowFav() {
        val tvResource = tvData.value
        if (tvResource != null) {
            val detailTvShow = tvResource.data

            if (detailTvShow != null) {
                val newState = !detailTvShow.favorite
                dataRepository.setTvShowFavorite(detailTvShow, newState)
            }
        }
    }

    fun unFavoriteTvShow() {
        val tvResource = tvData.value
        if (tvResource != null) {
            val detailTvShow = tvResource.data

            if (detailTvShow != null) {
                val newState = detailTvShow.favorite
                dataRepository.setTvShowFavorite(detailTvShow, newState)
            }
        }
    }

}