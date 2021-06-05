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

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var contentBinding: ContentDetailMovieBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contentBinding = binding.movieDetail

        setSupportActionBar(binding.toolbar)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailMovieVM::class.java]
        val extras = intent.extras
        checkSelected(extras, viewModel)

    }

    private fun checkSelected(extras: Bundle?, viewModel: DetailMovieVM) {
        when {
            null != extras -> {
                val movieId = extras.getString(EXTRA_MOVIESELECTED)
                when {
                    null != movieId -> {
                        viewModel.setSelectedMovie(movieId)
                        populateMovie(viewModel.getSelectedMovie(), binding, contentBinding)
                    }
                }
            }
        }
    }


    private fun populateMovie(movieModel: MovieModel, binding: ActivityDetailMovieBinding, contentBinding: ContentDetailMovieBinding) {

        Glide.with(this)
                .load(movieModel.imagePath)
                .transform(RoundedCorners(25))
                .placeholder(R.drawable.ic_loader)
                .error(R.drawable.ic_error)
                .into(binding.imagePosterDetail)
        binding.toolbarLayout.title = movieModel.title
        bindingListener(binding)
        contentBinding.movieDetailName.text = movieModel.title
        contentBinding.movieDetailRelease.text = movieModel.releaseDate
        contentBinding.movieDetailRatings.text = movieModel.movieRate
        contentBinding.movieDetailDesc.text = movieModel.description
        contentBinding.movieDetailGenre.text = movieModel.genres
        contentBinding.movieDetailLanguage.text = movieModel.originalLanguage
        contentBinding.movieDetailRuntime.text = movieModel.runTime
        contentBinding.movieDetailDirectors.text = movieModel.filmDirector


    }

    private fun bindingListener(binding: ActivityDetailMovieBinding) {
        binding.shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)

            val shareScript =
                    "${resources.getString(R.string.share_1)} ${
                        contentBinding.movieDetailName.text
                    }, ${resources.getString(R.string.share_2)} ${contentBinding.movieDetailRatings.text}"


            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, contentBinding.movieDetailName.text)
            intent.putExtra(Intent.EXTRA_TEXT, shareScript)
            startActivity(Intent.createChooser(intent, resources.getString(R.string.share_title)))
        }
    }

}