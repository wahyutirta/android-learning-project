package com.example.mynetflix.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieModel (
    var id: String,
    var title: String,
    var releaseDate: String,
    var movieRate: String,
    var description: String,
    var genres: String,
    var originalLanguage: String,
    var runTime: String,
    var imagePath: Int,
    var filmDirector: String
) : Parcelable