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
import java.lang.StringBuilder


class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TVSELECTED = "extra_tvselected"
    }

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var contentBinding: ContentDetailTvshowBinding
    private lateinit var viewModel: DetailTvShowVM
    private var state: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contentBinding = binding.contentTvShow

        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this, ViewModelFactory.getTvShowInstance(this))[DetailTvShowVM::class.java]
        val extras = intent.extras
        checkSelected(extras, viewModel, binding)


    }

    fun checkSelected(extras: Bundle?, viewModel: DetailTvShowVM, binding: ActivityDetailTvShowBinding) {
        if (null != extras) {
            val tvShowId = extras.getString(EXTRA_TVSELECTED)
            if (null != tvShowId) {
                observe(tvShowId, viewModel, binding)
            }
        }
    }
    private fun observe(tvShowId: String, viewModel: DetailTvShowVM, binding: ActivityDetailTvShowBinding){
        viewModel.setSelectedTvShow(tvShowId)

        viewModel.tvData.observe(this, { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    Status.LOADING -> {
                        onProgress(true, binding)
                    }
                    Status.SUCCESS -> if (tvShow.data != null) {
                        onProgress(false, binding)
                        populateTvShow(tvShow.data, binding, contentBinding)
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

    private fun setLoveState(state: Boolean, binding: ActivityDetailTvShowBinding) {
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

    private fun onProgress(state: Boolean, binding: ActivityDetailTvShowBinding){
        when (state){
            true -> {
                binding.loveBtn.visibility = View.INVISIBLE
                binding.shareBtn.visibility = View.INVISIBLE
                binding.contentTv.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.VISIBLE
            }
            false -> {
                binding.progressBar.visibility = View.GONE
                binding.contentTv.visibility = View.VISIBLE
                binding.shareBtn.visibility = View.VISIBLE
                binding.loveBtn.visibility = View.VISIBLE
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
        state = tvShowModel.favorite
        setLoveState(state, binding)
        bindingListener(tvShowModel, binding)

    }

    private fun bindingListener(tvShowModel: TvShowModel, binding: ActivityDetailTvShowBinding) {
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
        binding.loveBtn.setOnClickListener {
            val message = StringBuilder(tvShowModel.title).append(" ", if (tvShowModel.favorite) resources.getString(R.string.unlove) else resources.getString(R.string.love))
            Toast.makeText(
                applicationContext,
                message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.favoriteHandler()
        }

    }
}