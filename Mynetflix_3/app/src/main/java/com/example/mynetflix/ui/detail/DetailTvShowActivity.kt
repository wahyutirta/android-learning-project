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
import com.example.mynetflix.databinding.ActivityDetailTvShowBinding
import com.example.mynetflix.databinding.ContentDetailTvshowBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.vo.Status


class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TVSELECTED = "extra_tvselected"
    }

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var contentBinding: ContentDetailTvshowBinding
    private lateinit var viewModel: DetailTvShowVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contentBinding = binding.contentTvShow

        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailTvShowVM::class.java]
        val extras = intent.extras
        checkSelected(extras, viewModel)
        if (null != extras) {
            val tvShowId = extras.getString(EXTRA_TVSELECTED)
            if (null != tvShowId) {
                viewModel.setSelectedTvShow(tvShowId)

                viewModel.tvData.observe(this, { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.loveBtn.visibility = View.INVISIBLE
                                binding.shareBtn.visibility = View.INVISIBLE
                                binding.contentTv.visibility = View.INVISIBLE
                            }
                            Status.SUCCESS -> if (tvShow.data != null) {
                                binding.progressBar.visibility = View.GONE
                                binding.contentTv.visibility = View.VISIBLE
                                binding.shareBtn.visibility = View.VISIBLE
                                binding.loveBtn.visibility = View.VISIBLE
                                val state = tvShow.data.favorite
                                setBookmarkState(state)

                                populateTvShow(tvShow.data, binding, contentBinding)
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
                    binding.loveBtn.setOnClickListener {
                        viewModel.setTvShowFav()
                    }

                })
            }
        }

    }

    fun checkSelected(extras: Bundle?, viewModel: DetailTvShowVM) {
        
    }
    private fun observe(viewModel: DetailTvShowVM){
        
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

    private fun onProgress(state: Boolean){
        when (state){
            true -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.contentTv.visibility = View.INVISIBLE
            }
            false -> {
                binding.progressBar.visibility = View.GONE
                binding.contentTv.visibility = View.VISIBLE
            }
        }
    }

    private fun populateTvShow(tvShowModel: TvShowModel, binding: ActivityDetailTvShowBinding, contentBinding: ContentDetailTvshowBinding, ) {

        binding.toolbarLayout.title = tvShowModel.title


        Glide.with(this)
                .load(tvShowModel.imagePath)
                .transform(RoundedCorners(25))
                .placeholder(R.drawable.ic_loader)
                .error(R.drawable.ic_error)
                .into(binding.imagePosterDetailTv)
        contentBinding.tvShowTitle.text = tvShowModel.title
        contentBinding.tvShowDetailRelease.text = tvShowModel.releaseDate
        contentBinding.tvShowDetailRatings.text = tvShowModel.ratings
        contentBinding.tvShowDetailDesc.text = tvShowModel.description
        contentBinding.tvShowDetailGenre.text = tvShowModel.genre
        contentBinding.tvShowDetailLang.text = tvShowModel.originalLanguage
        contentBinding.tvShowDetailNumepisodes.text = tvShowModel.numOfEpisodes
        contentBinding.tvShowDetailNumseason.text = tvShowModel.numOfSeasons
        contentBinding.tvShowDetailRuntime.text = tvShowModel.runTimes
        contentBinding.tvShowDetailCreators.text = tvShowModel.creators
        bindingListener(binding)

    }

    private fun bindingListener(binding: ActivityDetailTvShowBinding) {
        binding.shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val shareScript =
                    "${resources.getString(R.string.share_1)} ${
                        contentBinding.tvShowTitle.text
                    }, ${resources.getString(R.string.share_2)} ${contentBinding.tvShowDetailRatings.text}"
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, contentBinding.tvShowTitle.text)
            intent.putExtra(Intent.EXTRA_TEXT, shareScript)
            startActivity(Intent.createChooser(intent, resources.getString(R.string.share_title)))
        }

    }
}