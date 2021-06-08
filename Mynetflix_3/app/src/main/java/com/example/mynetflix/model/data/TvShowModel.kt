package com.example.mynetflix.model.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynetflix.BuildConfig.TB_TVSHOW
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "$TB_TVSHOW")
data class TvShowModel(
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

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String,

    @ColumnInfo(name = "numOfEpisodes")
    var numOfEpisodes: String,

    @ColumnInfo(name = "numOfSeasons")
    var numOfSeasons: String,

    @ColumnInfo(name = "runTimes")
    var runTimes: String,

    @ColumnInfo(name = "imagePath")
    var imagePath: String,

    @ColumnInfo(name = "creators")
    var creators: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

) : Parcelable