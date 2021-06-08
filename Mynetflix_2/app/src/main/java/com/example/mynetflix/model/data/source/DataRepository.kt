package com.example.mynetflix.model.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.data.source.remote.response.MovieResponse
import com.example.mynetflix.model.data.source.remote.response.TvShowResponse


class DataRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    DataSourceInterface {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(remoteData: RemoteDataSource): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteData).apply { instance = this }
            }
    }

    var mList = ArrayList<MovieModel>()
    var mResult = MutableLiveData<List<MovieModel>>()

    var mDetailResult = MutableLiveData<MovieModel>()
    lateinit var movie: MovieModel

    var tResult = MutableLiveData<List<TvShowModel>>()
    var tList = ArrayList<TvShowModel>()

    var tDetailResult = MutableLiveData<TvShowModel>()
    lateinit var tvShow: TvShowModel

    override fun getMovies(): LiveData<List<MovieModel>> {


        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMovieReceived(movieResponse: List<MovieResponse>) {

                for (response in movieResponse) {
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
                    mList.add(movie)
                }
                mResult.postValue(mList)
            }

        })

        return mResult
    }

    override fun getMoviesDetail(movieId: String): LiveData<MovieModel> {

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMovieReceived(movieResponse: List<MovieResponse>) {

                for (response in movieResponse) {
                    if (response.id == movieId) {
                        movie = MovieModel(
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
                    }
                }
                mDetailResult.postValue(movie)
            }

        })

        return mDetailResult
    }


    override fun getTvShow(): LiveData<List<TvShowModel>> {

        remoteDataSource.getTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onTvShowReceived(tvShowResponse: List<TvShowResponse>) {

                for (response in tvShowResponse) {
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
                    tList.add(tvShow)
                }
                tResult.postValue(tList)
            }

        })

        return tResult
    }


    override fun getTvShowDetail(tvShowId: String): LiveData<TvShowModel> {


        remoteDataSource.getTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onTvShowReceived(tvShowResponse: List<TvShowResponse>) {

                for (response in tvShowResponse) {
                    if (response.id == tvShowId) {
                        tvShow = TvShowModel(
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
                    }
                }
                tDetailResult.postValue(tvShow)
            }

        })

        return tDetailResult
    }

}