package com.example.mynetflix.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ActivityDetailMovieBinding
import com.example.mynetflix.databinding.ActivityDetailTvShowBinding
import com.example.mynetflix.databinding.ContentDetailMovieBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.vo.Status
import java.lang.StringBuilder


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIESELECTED = "extra_movieselected"
    }

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var contentBinding: ContentDetailMovieBinding
    private lateinit var viewModel: DetailMovieVM
    private var state: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contentBinding = binding.movieDetail

        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailMovieVM::class.java]
        val extras = intent.extras
        checkSelected(extras, viewModel, binding)

    }

    private fun setBookmarkState(state: Boolean, binding: ActivityDetailMovieBinding) {
        if (state) {
            binding.loveBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite
                )
            )
        } else {
            binding.loveBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_unfavorite
                )
            )
        }
    }

    private fun checkSelected(extras: Bundle?, viewModel: DetailMovieVM, binding: ActivityDetailMovieBinding) {
        if (null != extras) {
            val movieId = extras.getString(EXTRA_MOVIESELECTED)
            if (null != movieId) {
                observe(movieId, viewModel, binding)
            }
        }
    }

    private fun observe(movieId: String, viewModel: DetailMovieVM, binding: ActivityDetailMovieBinding){
        viewModel.setSelectedMovie(movieId)

        viewModel.movieData.observe(this, { movie ->

            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> {
                        onProgress(true, binding)
                    }
                    Status.SUCCESS -> if (movie.data != null) {
                        onProgress(false, binding)
                        populateMovie(movie.data, binding, contentBinding)
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        val message = StringBuilder(R.string.fail_message)
                        Toast.makeText(
                            applicationContext,
                            message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        })
    }

    private fun onProgress(state: Boolean, binding: ActivityDetailMovieBinding){
        when (state){
            true -> {
                binding.loveBtn.visibility = View.INVISIBLE
                binding.shareBtn.visibility = View.INVISIBLE
                binding.contentMovie.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.VISIBLE
            }
            false -> {
                binding.progressBar.visibility = View.GONE
                binding.contentMovie.visibility = View.VISIBLE
                binding.shareBtn.visibility = View.VISIBLE
                binding.loveBtn.visibility = View.VISIBLE
            }
        }
    }


    private fun populateMovie( movieModel: MovieModel, binding: ActivityDetailMovieBinding, contentBinding: ContentDetailMovieBinding) {

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
        contentBinding.movieDetailRatings.text = movieModel.ratings
        contentBinding.movieDetailDesc.text = movieModel.description
        contentBinding.movieDetailGenre.text = movieModel.genres
        contentBinding.movieDetailLanguage.text = movieModel.originalLanguage
        contentBinding.movieDetailRuntime.text = movieModel.runTime
        contentBinding.movieDetailDirectors.text = movieModel.filmDirector
        state = movieModel.favorite
        setBookmarkState(state, binding)
        bindingListener(movieModel, binding)

    }

    private fun bindingListener(movieModel: MovieModel, binding: ActivityDetailMovieBinding) {
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
        binding.loveBtn.setOnClickListener {
            val message = StringBuilder(movieModel.title).append(" ", if (movieModel.favorite) resources.getString(R.string.unlove) else resources.getString(R.string.love))
            Toast.makeText(
                applicationContext,
                message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.favoriteHandler()
        }
    }

}