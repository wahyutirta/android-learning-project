package com.example.mygithubproject.modelandservice.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(
    entities = [FavoriteUser::class],
    version = 1
)
abstract class DBUser : RoomDatabase() {

    companion object {
        private var INSTANCE: DBUser? = null

        fun getDatabase(context: Context): DBUser? {
            if (INSTANCE == null) {
                synchronized(DBUser::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DBUser::class.java,
                        "db_user"
                    ).build()

                }
            }
            return INSTANCE
        }

    }

    abstract fun favoriteUserDao(): FavoriteUserDao

}