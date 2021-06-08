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
import com.example.mynetflix.databinding.ContentDetailMovieBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.vo.Status


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
        if (null != extras) {
            val movieId = extras.getString(EXTRA_MOVIESELECTED)
            if (null != movieId) {
                viewModel.setSelectedMovie(movieId)


                viewModel.movieData.observe(this, { movie ->

                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.shareBtn.visibility = View.INVISIBLE
                                binding.loveBtn.visibility = View.INVISIBLE
                                binding.contentMovie.visibility =
                                    View.INVISIBLE
                            }
                            Status.SUCCESS -> if (movie.data != null) {
                                binding.progressBar.visibility = View.GONE
                                binding.contentMovie.visibility = View.VISIBLE
                                binding.shareBtn.visibility = View.VISIBLE
                                binding.loveBtn.visibility = View.VISIBLE
                                val state = movie.data.favorite
                                setBookmarkState(state)
                                populateMovie(movie.data, binding, contentBinding)

                            }
                            Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    //add to favorite
                    binding.loveBtn.setOnClickListener {
                        viewModel.setFavorite()
                    }
                })
            }
        }
    }

    private fun setBookmarkState(state: Boolean) {
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

    private fun checkSelected(extras: Bundle?, viewModel: DetailMovieVM) {

    }

    private fun observe(viewModel: DetailMovieVM){

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
        contentBinding.movieDetailRatings.text = movieModel.ratings
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