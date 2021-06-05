package com.example.mynetflix.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ActivityDetailTvShowBinding
import com.example.mynetflix.databinding.ContentDetailTvshowBinding
import com.example.mynetflix.model.TvShowModel


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
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailTvShowVM::class.java]
        val extras = intent.extras
        checkSelected(extras, viewModel)

    }

    fun checkSelected(extras: Bundle?, viewModel: DetailTvShowVM) {
        when {
            extras != null -> {
                val tvShowId = extras.getString(EXTRA_TVSELECTED)
                when {
                    tvShowId != null -> {
                        viewModel.setSelectedTvShow(tvShowId)
                        populateTvShow(viewModel.getSelectedTvShow(), contentBinding, binding)
                    }
                }
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