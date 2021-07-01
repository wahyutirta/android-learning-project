package com.example.mynetflix.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynetflix.di.Injection

import com.example.mynetflix.model.data.source.remote.repository.MovieRepository
import com.example.mynetflix.model.data.source.remote.repository.TvShowRepository
import com.example.mynetflix.ui.detail.DetailMovieVM
import com.example.mynetflix.ui.detail.DetailTvShowVM
import com.example.mynetflix.ui.main.favorite.favmovie.FavMovieFragmentVM
import com.example.mynetflix.ui.main.favorite.favtvshow.FavTvShowFragmentVM
import com.example.mynetflix.ui.movie.MovieVM
import com.example.mynetflix.ui.tvshow.TvShowVM

class ViewModelFactory(private var mMovieRepository: MovieRepository, private var mTvShowRepository: TvShowRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getMovieInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMovieRepository(context), Injection.provideTvShowRepository(context)).apply {
                    instance = this
                }
            }

        fun getTvShowInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMovieRepository(context), Injection.provideTvShowRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {

            modelClass.isAssignableFrom(DetailMovieVM::class.java) -> DetailMovieVM(mMovieRepository) as T
            modelClass.isAssignableFrom(DetailTvShowVM::class.java) -> DetailTvShowVM(mTvShowRepository) as T
            modelClass.isAssignableFrom(MovieVM::class.java) -> MovieVM(mMovieRepository) as T
            modelClass.isAssignableFrom(TvShowVM::class.java) -> TvShowVM(mTvShowRepository) as T
            modelClass.isAssignableFrom(FavMovieFragmentVM::class.java) -> FavMovieFragmentVM(mMovieRepository) as T
            modelClass.isAssignableFrom(FavTvShowFragmentVM::class.java) -> FavTvShowFragmentVM(mTvShowRepository) as T

            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}