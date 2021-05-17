package com.example.mynetflix.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ActivityDetailMovieBinding
import com.example.mynetflix.databinding.ContentDetailMovieBinding
import com.example.mynetflix.model.MovieModel


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private lateinit var detailMovieBinding: ContentDetailMovieBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)
        detailMovieBinding = activityDetailMovieBinding.movieDetail

        setSupportActionBar(activityDetailMovieBinding.toolbar)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieVM::class.java]

        val extras = intent.extras


        when{
            null != extras -> {
                val movieId = extras.getString(EXTRA_MOVIE)
                when {
                    null != movieId -> {
                        viewModel.setSelectedMovie(movieId)
                        populateMovie(viewModel.getMovie())
                    }
                }
            }
        }
    }
    private fun populateMovie(movieModel: MovieModel) {
        detailMovieBinding.movieName.text = movieModel.title
        detailMovieBinding.movieReleaseDetail.text = movieModel.releaseDate
        detailMovieBinding.movieRateDetail.text = movieModel.movieRate
        detailMovieBinding.movieDescription.text = movieModel.description
        detailMovieBinding.movieGenreDetail.text = movieModel.genres
        detailMovieBinding.movieLocation.text = movieModel.shotingLocation
        activityDetailMovieBinding.toolbarLayout.title = movieModel.title

        Glide.with(this)
            .load(movieModel.imagePath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loader).error(R.drawable.ic_error))
            .into(activityDetailMovieBinding.imagePosterDetail)

        activityDetailMovieBinding.fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
/*
            val shareBody = StringBuilder(resources.getString(R.string.share_body1))
                .append(detailMovieBinding.movieName.text, resources.getString(R.string.share_body2))
                .append( detailMovieBinding.movieRateDetail.text, resources.getString(R.string.share_body3
                ))

 */


            val shareBody =
                "${resources.getString(R.string.share_body1)} ${
                    detailMovieBinding.movieName.text
                }, ${resources.getString(R.string.share_body2)} ${detailMovieBinding.movieRateDetail.text}, ${
                    resources.getString(
                        R.string.share_body3
                    )
                }"




            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, detailMovieBinding.movieName.text)
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(intent, resources.getString(R.string.share_title)))
        }

    }
}