package com.example.mynetflix.model.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynetflix.BuildConfig.TB_MOVIE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "$TB_MOVIE")
data class MovieModel(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "ratings")
    var ratings: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String,

    @ColumnInfo(name = "runTime")
    var runTime: String,

    @ColumnInfo(name = "imagePath")
    var imagePath: String,

    @ColumnInfo(name = "filmDirector")
    var filmDirector: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

) : Parcelable