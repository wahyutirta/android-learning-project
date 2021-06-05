package com.example.mynetflix.model.utils

import android.content.Context
import com.example.mynetflix.model.data.source.remote.response.MovieResponse
import com.example.mynetflix.model.data.source.remote.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("DataResponse.json").toString())
            val listArray = responseObject.getJSONArray("movie")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val releaseDate = movie.getString("releaseDate")
                val movieRate = movie.getString("movieRate")
                val description = movie.getString("description")
                val genres = movie.getString("genres")
                val originalLanguage = movie.getString("originalLanguage")
                val runTime = movie.getString("runTime")
                val imagePath = movie.getString("imagePath")
                val filmDirector = movie.getString("filmDirectors")


                val movieResponse = MovieResponse(
                        id,
                        title,
                        releaseDate,
                        movieRate,
                        description,
                        genres,
                        originalLanguage,
                        runTime,
                        imagePath,
                        filmDirector,
                )
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTvShow(): List<TvShowResponse> {
        val list = ArrayList<TvShowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("DataResponse.json").toString())
            val listArray = responseObject.getJSONArray("tvshow")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val id = tvShow.getString("id")
                val title = tvShow.getString("title")
                val releaseDate = tvShow.getString("releaseDate")
                val ratings = tvShow.getString("ratings")
                val description = tvShow.getString("description")
                val tvShowGenre = tvShow.getString("tvShowGenre")
                val originalLanguage = tvShow.getString("originalLanguage")
                val numOfEpisodes = tvShow.getString("numOfEpisodes")
                val numOfSeasons = tvShow.getString("numOfSeasons")
                val runTimes = tvShow.getString("runTimes")
                val imagePath = tvShow.getString("imagePath")
                val creators = tvShow.getString("creators")


                val tvShowResponse = TvShowResponse(
                        id,
                        title,
                        releaseDate,
                        ratings,
                        description,
                        tvShowGenre,
                        originalLanguage,
                        numOfEpisodes,
                        numOfSeasons,
                        runTimes,
                        imagePath,
                        creators,
                )
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

}