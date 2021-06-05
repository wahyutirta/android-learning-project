package com.example.mynetflix.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ActivityDetailTvShowBinding
import com.example.mynetflix.databinding.ContentDetailTvshowBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.model.data.TvShowModel


class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TVSELECTED = "extra_tvselected"
    }

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var contentBinding: ContentDetailTvshowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contentBinding = binding.contentTvShow

        setSupportActionBar(binding.toolbar)
        val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailTvShowVM::class.java]
        val extras = intent.extras
        checkSelected(extras, viewModel)

    }

    fun checkSelected(extras: Bundle?, viewModel: DetailTvShowVM) {
        if (null != extras) {
            val tvShowId = extras.getString(EXTRA_TVSELECTED)
            if (null != tvShowId) {
                onProgress(true)

                viewModel.setSelectedTvShow(tvShowId)
                viewModel.getTvShow().observe(this, { tvShow ->
                    onProgress(false)
                    populateTvShow(tvShow, contentBinding, binding)
                })
            }
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

    private fun populateTvShow(tvShowModel: TvShowModel, contentBinding: ContentDetailTvshowBinding, binding: ActivityDetailTvShowBinding) {

        binding.toolbarLayout.title = tvShowModel.title
        bindingListener(binding)

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
        contentBinding.tvShowDetailGenre.text = tvShowModel.tvShowGenre
        contentBinding.tvShowDetailLang.text = tvShowModel.originalLanguage
        contentBinding.tvShowDetailNumepisodes.text = tvShowModel.numOfEpisodes
        contentBinding.tvShowDetailNumseason.text = tvShowModel.numOfSeasons
        contentBinding.tvShowDetailRuntime.text = tvShowModel.runTimes
        contentBinding.tvShowDetailCreators.text = tvShowModel.creators


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