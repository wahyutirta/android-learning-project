package com.example.mynetflix.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TvShowModel(
    var id: String,
    var title: String,
    var releaseDate: String,
    var ratings: String,
    var description: String,
    var tvShowGenre: String,
    var originalLanguage: String,
    var numOfEpisodes: String,
    var numOfSeasons: String,
    var runTimes: String,
    var imagePath: Int,
    var creators: String
) : Parcelable