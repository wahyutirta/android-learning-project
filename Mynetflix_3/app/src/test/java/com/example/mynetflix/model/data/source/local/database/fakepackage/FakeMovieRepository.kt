package com.example.mynetflix.model.data.source.local.database.fakepackage

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.source.MovieDataSourceInterface
import com.example.mynetflix.model.data.source.NetworkBoundResource
import com.example.mynetflix.model.data.source.local.database.MovieLocalDataSource
import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.data.source.remote.response.ApiResponse
import com.example.mynetflix.model.data.source.remote.response.MovieResponse
import com.example.mynetflix.model.utils.AppExecutors
import com.example.mynetflix.vo.Resource


class FakeMovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieDataSourceInterface {

    override fun getAllMoviesResource(): LiveData<Resource<PagedList<MovieModel>>> {
        return object :
            NetworkBoundResource<PagedList<MovieModel>, List<MovieResponse>>(appExecutors) {

            public override fun loadFromDB(): LiveData<PagedList<MovieModel>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(movieLocalDataSource.getAllMovie(), config).build()
            }

            override fun isFetch(data: PagedList<MovieModel>?): Boolean =
                data == null || data.isEmpty()
                //true

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
                movieLocalDataSource.insertMoviesList(movieList)
            }
        }.asLiveData()
    }

    override fun getMoviesDetail(movieId: String): LiveData<Resource<MovieModel>> {

        return object : NetworkBoundResource<MovieModel, List<MovieResponse>>(appExecutors) {

            public override fun loadFromDB(): LiveData<MovieModel> =
                movieLocalDataSource.getDetailMovieById(movieId)

            override fun isFetch(data: MovieModel?): Boolean =
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

    override fun getDetailMovieById(movieId: String): LiveData<MovieModel> =
        movieLocalDataSource.getDetailMovieById(movieId)


    override fun getAllMovie(): LiveData<PagedList<MovieModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(movieLocalDataSource.getAllMovie(), config).build()
    }

    override fun setMovieFavoriteState(movie: MovieModel, NewState: Boolean) {
        //appExecutors.diskIO().execute { movieLocalDataSource.setMovieFavorite(movie, NewState) }
        movieLocalDataSource.setMovieFavorite(movie, NewState)
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(movieLocalDataSource.getFavoriteMovie(), config).build()
    }

}