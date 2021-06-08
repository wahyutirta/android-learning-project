package com.example.mynetflix.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ActivityDetailMovieBinding
import com.example.mynetflix.databinding.ContentDetailMovieBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.model.data.MovieModel


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIESELECTED = "extra_movieselected"
    }

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var contentBinding: ContentDetailMovieBinding
    private lateinit var viewModel: DetailMovieVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contentBinding = binding.movieDetail

        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailMovieVM::class.java]
        val extras = intent.extras
        checkSelected(extras, viewModel)

    }

    private fun checkSelected(extras: Bundle?, viewModel: DetailMovieVM) {
        if (null != extras) {
            val movieId = extras.getString(EXTRA_MOVIESELECTED)
            if (null != movieId) {
                onProgress(true)
                viewModel.setSelectedMovie(movieId)
                observe(viewModel)
            }
        }
    }

    private fun observe(viewModel: DetailMovieVM){
        viewModel.getMovie().observe(this, { movie ->
            onProgress(false)
            populateMovie(movie, binding, contentBinding)
        })
    }

    private fun onProgress(state: Boolean){
        when (state){
            true -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.contentMovie.visibility = View.INVISIBLE
            }
            false -> {
                binding.progressBar.visibility = View.GONE
                binding.contentMovie.visibility = View.VISIBLE
            }
        }
    }


    private fun populateMovie(movieModel: MovieModel, binding: ActivityDetailMovieBinding, contentBinding: ContentDetailMovieBinding) {

        Glide.with(this)
                //.load(resources.getIdentifier(img, "drawable", packageName))
                .load(movieModel.imagePath)
                .transform(RoundedCorners(25))
                .placeholder(R.drawable.ic_loader)
                .error(R.drawable.ic_error)
                .into(binding.imagePosterDetail)
        binding.toolbarLayout.title = movieModel.title

        contentBinding.movieDetailName.text = movieModel.title
        contentBinding.movieDetailRelease.text = movieModel.releaseDate
        contentBinding.movieDetailRatings.text = movieModel.movieRate
        contentBinding.movieDetailDesc.text = movieModel.description
        contentBinding.movieDetailGenre.text = movieModel.genres
        contentBinding.movieDetailLanguage.text = movieModel.originalLanguage
        contentBinding.movieDetailRuntime.text = movieModel.runTime
        contentBinding.movieDetailDirectors.text = movieModel.filmDirector
        bindingListener(binding)

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