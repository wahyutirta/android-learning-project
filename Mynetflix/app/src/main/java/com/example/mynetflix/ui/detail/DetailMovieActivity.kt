package com.example.mynetflix.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ActivityDetailMovieBinding
import com.example.mynetflix.databinding.ContentDetailMovieBinding
import com.example.mynetflix.model.MovieModel


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIESELECTED = "extra_movieselected"
    }

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private lateinit var detailMovieBinding: ContentDetailMovieBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)
        detailMovieBinding = activityDetailMovieBinding.movieDetail

        setSupportActionBar(activityDetailMovieBinding.toolbar)

        val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[DetailMovieVM::class.java]
        val extras = intent.extras
        checkSelected(extras, viewModel)

    }

    private fun checkSelected(extras: Bundle?, viewModel: DetailMovieVM){
        when{
            null != extras -> {
                val movieId = extras.getString(EXTRA_MOVIESELECTED)
                when {
                    null != movieId -> {
                        viewModel.setSelectedMovie(movieId)
                        populateMovie(viewModel.getSelectedMovie())
                    }
                }
            }
        }
    }


    private fun populateMovie(movieModel: MovieModel) {

        Glide.with(this)
                .load(movieModel.imagePath)
                .transform(RoundedCorners(25))
                .placeholder(R.drawable.ic_loader)
                .error(R.drawable.ic_error)
                .into(activityDetailMovieBinding.imagePosterDetail)

        detailMovieBinding.movieDetailName.text = movieModel.title
        detailMovieBinding.movieDetailRelease.text = movieModel.releaseDate
        detailMovieBinding.movieDetailRatings.text = movieModel.movieRate
        detailMovieBinding.movieDetailDesc.text = movieModel.description
        detailMovieBinding.movieDetailGenre.text = movieModel.genres
        detailMovieBinding.movieDetailLanguage.text = movieModel.originalLanguage
        detailMovieBinding.movieDetailRuntime.text = movieModel.runTime
        detailMovieBinding.movieDetailDirectors.text = movieModel.filmDirector
        activityDetailMovieBinding.toolbarLayout.title = movieModel.title
        activityDetailMovieBinding.shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)

            val shareScript =
                "${resources.getString(R.string.share_1)} ${
                    detailMovieBinding.movieDetailName.text
                }, ${resources.getString(R.string.share_2)} ${detailMovieBinding.movieDetailRatings.text}"


            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, detailMovieBinding.movieDetailName.text)
            intent.putExtra(Intent.EXTRA_TEXT, shareScript)
            startActivity(Intent.createChooser(intent, resources.getString(R.string.share_title)))
        }

    }
}