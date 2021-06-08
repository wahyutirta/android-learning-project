package com.example.mynetflix.model.data.source.remote

import android.os.Looper
import com.example.mynetflix.model.data.source.remote.response.MovieResponse
import com.example.mynetflix.model.data.source.remote.response.TvShowResponse
import com.example.mynetflix.model.utils.EspressoIdlingResource
import com.example.mynetflix.model.utils.JsonHelper


class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = android.os.Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 3000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onMovieReceived(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onMovieReceived(movieResponse: List<MovieResponse>)
    }

    fun getTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onTvShowReceived(jsonHelper.loadTvShow())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }


    interface LoadTvShowCallback {
        fun onTvShowReceived(tvShowResponse: List<TvShowResponse>)
    }


}