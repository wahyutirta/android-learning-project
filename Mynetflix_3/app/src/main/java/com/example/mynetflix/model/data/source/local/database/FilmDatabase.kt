package com.example.mynetflix.model.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynetflix.BuildConfig.DB_NAME
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel


@Database(entities = [MovieModel::class, TvShowModel::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao

    companion object {
        @Volatile
        private var INSTANCE: FilmDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FilmDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FilmDatabase::class.java,
                    "$DB_NAME"
                ).allowMainThreadQueries().build().apply { INSTANCE = this }
            }
    }
}