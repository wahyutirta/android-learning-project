package com.example.mynetflix.ui.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.model.data.source.DataSourceInterface
import com.example.mynetflix.model.data.source.remote.RemoteDataSource
import com.example.mynetflix.model.data.source.remote.response.MovieResponse
import com.example.mynetflix.model.data.source.remote.response.TvShowResponse


class FakeRepository (private val remoteDataSource: RemoteDataSource):
    DataSourceInterface {

    companion object {
        @Volatile
        private var instance: FakeRepository? = null

        fun getInstance(remoteData: RemoteDataSource): FakeRepository =
            instance ?: synchronized(this) {
                instance ?: FakeRepository(remoteData).apply { instance = this }
            }
    }

    var movieList = ArrayList<MovieModel>()
    var movieResult = MutableLiveData<List<MovieModel>>()

    var tvShowResult = MutableLiveData<List<TvShowModel>>()
    var tvShowList = ArrayList<TvShowModel>()

    var movieDetailResult = MutableLiveData<MovieModel>()
    lateinit var movie: MovieModel

    var tvShowDetailResult = MutableLiveData<TvShowModel>()
    lateinit var tvShow: TvShowModel

    override fun getAllMovies(): LiveData<List<MovieModel>> {


        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMovieReceived(movieResponse: List<MovieResponse>) {

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
                    movieList.add(movie)
                }
                movieResult.postValue(movieList)
            }

        })

        return movieResult
    }


    override fun getAllTvShow(): LiveData<List<TvShowModel>> {

        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<TvShowResponse>) {

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
                    tvShowList.add(tvShow)
                }
                tvShowResult.postValue(tvShowList)
            }

        })

        return tvShowResult
    }

    override fun getMoviesDetail(movieId: String): LiveData<MovieModel> {

        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMovieReceived(movieResponse: List<MovieResponse>) {

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
                movieDetailResult.postValue(movie)
            }

        })

        return movieDetailResult
    }

    override fun getTvShowDetail(tvShowId: String): LiveData<TvShowModel> {


        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<TvShowResponse>) {

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
                tvShowDetailResult.postValue(tvShow)
            }

        })

        return tvShowDetailResult
    }

}