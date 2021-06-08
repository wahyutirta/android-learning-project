package com.example.mynetflix.model.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.local.database.LocalDataSource
import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.data.source.remote.response.ApiResponse
import com.example.mynetflix.model.data.source.remote.response.MovieResponse
import com.example.mynetflix.model.data.source.remote.response.TvShowResponse
import com.example.mynetflix.model.utils.AppExecutors
import com.example.mynetflix.vo.Resource


class DataRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    DataSourceInterface {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(
                    remoteData,
                    localDataSource,
                    appExecutors
                ).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieModel>>> {
        return object :
            NetworkBoundResource<PagedList<MovieModel>, List<MovieResponse>>(appExecutors) {

            public override fun loadFromDB(): LiveData<PagedList<MovieModel>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieModel>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieModel>()
                for (response in data) {
                    val movie = MovieModel(
                        response.id,
                        response.title,
                        response.releaseDate,
                        response.ratings,
                        response.description,
                        response.genres,
                        response.originalLanguage,
                        response.runTime,
                        response.imagePath,
                        response.filmDirector,
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMoviesList(movieList)
            }
        }.asLiveData()
    }


    override fun getAllTvShow(): LiveData<Resource<PagedList<TvShowModel>>> {

        return object :
            NetworkBoundResource<PagedList<TvShowModel>, List<TvShowResponse>>(appExecutors) {

            public override fun loadFromDB(): LiveData<PagedList<TvShowModel>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
            }


            override fun shouldFetch(data: PagedList<TvShowModel>?): Boolean =
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
                localDataSource.insertTvShowList(tvShowList)
            }
        }.asLiveData()
    }

    override fun getMoviesDetail(movieId: String): LiveData<Resource<MovieModel>> {

        return object : NetworkBoundResource<MovieModel, List<MovieResponse>>(appExecutors) {

            public override fun loadFromDB(): LiveData<MovieModel> =
                localDataSource.getDetailMovieById(movieId)

            override fun shouldFetch(data: MovieModel?): Boolean =
                data?.id == null || data.id.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieModel>()
                for (response in data) {
                    val movie = MovieModel(
                        response.id,
                        response.title,
                        response.releaseDate,
                        response.ratings,
                        response.description,
                        response.genres,
                        response.originalLanguage,
                        response.runTime,
                        response.imagePath,
                        response.filmDirector,
                    )
                    movieList.add(movie)
                }
            }
        }.asLiveData()
    }

    override fun getTvShowDetail(tvShowId: String): LiveData<Resource<TvShowModel>> {

        return object : NetworkBoundResource<TvShowModel, List<TvShowResponse>>(appExecutors) {

            public override fun loadFromDB(): LiveData<TvShowModel> =
                localDataSource.getDetailTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowModel?): Boolean =
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

    override fun setFavoriteMovie(movie: MovieModel) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie) }
    }



    override fun setMovieFavorite(movie: MovieModel, NewState: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, NewState) }
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }


    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun getDetailTvShowById(tvShowId: String) : LiveData<TvShowModel> =
        localDataSource.getDetailTvShowById(tvShowId)

    override fun getDetailMovieById(movieId: String): LiveData<MovieModel> =
        localDataSource.getDetailMovieById(movieId)


    override fun getAllMovie(): LiveData<PagedList<MovieModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllMovie(), config).build()
    }

    override fun getAllTvShows(): LiveData<PagedList<TvShowModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
    }

    override fun setTvShowFavorite(tvShow: TvShowModel, NewState: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, NewState) }
    }


}