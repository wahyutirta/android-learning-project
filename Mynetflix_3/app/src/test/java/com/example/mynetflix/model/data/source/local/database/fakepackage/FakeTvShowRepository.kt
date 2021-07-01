package com.example.mynetflix.model.data.source.local.database.fakepackage

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.NetworkBoundResource
import com.example.mynetflix.model.data.source.TvShowDataSourceInterface
import com.example.mynetflix.model.data.source.local.database.TvShowLocalDataSource

import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.data.source.remote.response.ApiResponse
import com.example.mynetflix.model.data.source.remote.response.TvShowResponse
import com.example.mynetflix.model.utils.AppExecutors
import com.example.mynetflix.vo.Resource


class FakeTvShowRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val appExecutors: AppExecutors
) :
    TvShowDataSourceInterface {

    override fun getAllTvShowResource(): LiveData<Resource<PagedList<TvShowModel>>> {

        return object :
            NetworkBoundResource<PagedList<TvShowModel>, List<TvShowResponse>>(appExecutors) {

            public override fun loadFromDB(): LiveData<PagedList<TvShowModel>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(tvShowLocalDataSource.getAllTvShow(), config).build()
            }


            override fun isFetch(data: PagedList<TvShowModel>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowModel>()
                for (response in data) {
                    val tvShow = TvShowModel(
                        response.id,
                        response.title,
                        response.releaseDate,
                        response.ratings,
                        response.description,
                        response.tvShowGenre,
                        response.originalLanguage,
                        response.numOfEpisodes,
                        response.numOfSeasons,
                        response.runTimes,
                        response.imagePath,
                        response.creators,
                    )
                    tvShowList.add(tvShow)
                }
                tvShowLocalDataSource.insertTvShowList(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTvShowDetail(tvShowId: String): LiveData<Resource<TvShowModel>> {

        return object : NetworkBoundResource<TvShowModel, List<TvShowResponse>>(appExecutors) {

            public override fun loadFromDB(): LiveData<TvShowModel> =
                tvShowLocalDataSource.getDetailTvShowById(tvShowId)

            override fun isFetch(data: TvShowModel?): Boolean =
                data?.id == null || data.id.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val movieList = ArrayList<TvShowModel>()
                for (response in data) {
                    val tvShow = TvShowModel(
                        response.id,
                        response.title,
                        response.releaseDate,
                        response.ratings,
                        response.description,
                        response.tvShowGenre,
                        response.originalLanguage,
                        response.numOfEpisodes,
                        response.numOfSeasons,
                        response.runTimes,
                        response.imagePath,
                        response.creators,
                    )
                    movieList.add(tvShow)
                }
            }
        }.asLiveData()
    }


    override fun getAllTvShow(): LiveData<PagedList<TvShowModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(tvShowLocalDataSource.getAllTvShow(), config).build()
    }

    override fun getDetailTvShowById(tvShowId: String): LiveData<TvShowModel> =
        tvShowLocalDataSource.getDetailTvShowById(tvShowId)

    override fun setTvShowFavoriteState(tvShow: TvShowModel, NewState: Boolean) {
        tvShowLocalDataSource.setTvShowFavorite(tvShow, NewState)
    }


    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(tvShowLocalDataSource.getFavoriteTvShow(), config).build()
    }
}