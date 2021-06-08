package com.example.mynetflix.model.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TvShowResponse(
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
        var imagePath: String,
        var creators: String
) : Parcelable