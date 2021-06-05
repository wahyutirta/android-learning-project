package com.example.mygithubproject.services.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mygithubproject.BuildConfig.TB_NAME
@Database(
    entities = [FavUsers::class],
    version = 1
)
abstract class DBUser : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: DBUser? = null

        fun getDatabase(context: Context): DBUser? {
            if (INSTANCE == null) {
                synchronized(DBUser::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DBUser::class.java,
                        "$TB_NAME"
                    ).fallbackToDestructiveMigration().build()

                }
            }
            return INSTANCE
        }

    }

    abstract fun favoriteUsersDao(): FavUsersDao

}