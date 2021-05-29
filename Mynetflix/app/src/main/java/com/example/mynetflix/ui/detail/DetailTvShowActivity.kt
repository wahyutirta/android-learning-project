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

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private lateinit var detailTvshowBinding: ContentDetailTvshowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(activityDetailTvShowBinding.root)

        detailTvshowBinding = activityDetailTvShowBinding.contentTvShow

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[DetailTvShowVM::class.java]
        val extras = intent.extras
        checkSelected(extras, viewModel)

    }
    fun checkSelected(extras : Bundle?, viewModel: DetailTvShowVM){
        when {
            extras != null -> {
                val tvShowId = extras.getString(EXTRA_TVSELECTED)
                when {
                    tvShowId != null -> {
                        viewModel.setSelectedTvShow(tvShowId)
                        populateTvShow(viewModel.getSelectedTvShow())
                    }
                }
            }
        }
    }

    private fun populateTvShow(tvShowModel: TvShowModel) {

        Glide.with(this)
                .load(tvShowModel.imagePath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loader).error(R.drawable.ic_error))
                .into(activityDetailTvShowBinding.imagePosterDetailTv)

        detailTvshowBinding.tvShowTitle.text = tvShowModel.title
        detailTvshowBinding.tvShowDetailRelease.text = tvShowModel.releaseDate
        detailTvshowBinding.tvShowDetailRatings.text = tvShowModel.ratings
        detailTvshowBinding.tvShowDetailDesc.text = tvShowModel.description
        detailTvshowBinding.tvShowDetailGenre.text = tvShowModel.tvShowGenre
        detailTvshowBinding.tvShowDetailLang.text = tvShowModel.originalLanguage
        detailTvshowBinding.tvShowDetailNumepisodes.text = tvShowModel.numOfEpisodes
        detailTvshowBinding.tvShowDetailNumseason.text = tvShowModel.numOfSeasons
        detailTvshowBinding.tvShowDetailRuntime.text = tvShowModel.runTimes
        detailTvshowBinding.tvShowDetailCreators.text = tvShowModel.creators
        activityDetailTvShowBinding.toolbarLayout.title = tvShowModel.title

        Glide.with(this)
            .load(tvShowModel.imagePath)
            .transform(RoundedCorners(25))
            .placeholder(R.drawable.ic_loader)
            .error(R.drawable.ic_error)
            .into(activityDetailTvShowBinding.imagePosterDetailTv)

        activityDetailTvShowBinding.shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val shareScript =
                "${resources.getString(R.string.share_1)} ${
                    detailTvshowBinding.tvShowTitle.text
                }, ${resources.getString(R.string.share_2)} ${detailTvshowBinding.tvShowDetailRatings.text}"
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, detailTvshowBinding.tvShowTitle.text)
            intent.putExtra(Intent.EXTRA_TEXT, shareScript)
            startActivity(Intent.createChooser(intent, resources.getString(R.string.share_title)))
        }

    }
}