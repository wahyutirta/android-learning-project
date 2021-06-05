package com.example.mynetflix.model.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieResponse (
        var id: String,
        var title: String,
        var releaseDate: String,
        var ratings: String,
        var description: String,
        var genres: String,
        var originalLanguage: String,
        var runTime: String,
        var imagePath: String,
        var filmDirector: String
) : Parcelable