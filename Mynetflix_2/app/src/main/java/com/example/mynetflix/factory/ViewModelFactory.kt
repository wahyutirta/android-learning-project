package com.example.mynetflix.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynetflix.DI.Injection
import com.example.mynetflix.model.data.source.DataRepository
import com.example.mynetflix.ui.detail.DetailMovieVM
import com.example.mynetflix.ui.detail.DetailTvShowVM
import com.example.mynetflix.ui.movie.MovieVM
import com.example.mynetflix.ui.tvshow.TvShowVM

class ViewModelFactory(private val mFilmRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailMovieVM::class.java) -> DetailMovieVM(mFilmRepository) as T
            modelClass.isAssignableFrom(DetailTvShowVM::class.java) -> DetailTvShowVM(mFilmRepository) as T
            modelClass.isAssignableFrom(MovieVM::class.java) -> MovieVM(mFilmRepository) as T
            modelClass.isAssignableFrom(TvShowVM::class.java) -> TvShowVM(mFilmRepository) as T

            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}