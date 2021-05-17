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
        const val EXTRA_TV = "extra_tv"
    }

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private lateinit var detailTvshowBinding: ContentDetailTvshowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(activityDetailTvShowBinding.root)


        detailTvshowBinding = activityDetailTvShowBinding.contentTvShow

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailTvShowVM::class.java]

        val extras = intent.extras
        when {
            extras != null -> {
                val tvShowId = extras.getString(EXTRA_TV)
                when {
                    tvShowId != null -> {
                        viewModel.setSelectedTvShow(tvShowId)
                        populateTvShow(viewModel.getTvShow())
                    }
                }
            }
        }
    }

    private fun populateTvShow(tvShowModel: TvShowModel) {
        detailTvshowBinding.tvShowName.text = tvShowModel.title
        detailTvshowBinding.tvShowReleaseDetail.text = tvShowModel.releaseDate
        detailTvshowBinding.tvShowRateDetail.text = tvShowModel.ratings
        detailTvshowBinding.tvShowDescDetail.text = tvShowModel.description
        detailTvshowBinding.tvShowGenreDetail.text = tvShowModel.tvShowGenre
        detailTvshowBinding.tvShowLocationDetail.text = tvShowModel.shotLocation
        detailTvshowBinding.tvShowTotalEpisodeDetail.text = tvShowModel.numOfEpisodes

        activityDetailTvShowBinding.toolbarLayout.title = tvShowModel.title

        Glide.with(this)
            .load(tvShowModel.imagePath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loader).error(R.drawable.ic_error))
            .into(activityDetailTvShowBinding.imagePosterDetailTv)

        activityDetailTvShowBinding.fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val shareBody =
                "${resources.getString(R.string.share_body1)} ${
                    detailTvshowBinding.tvShowName.text
                }, ${resources.getString(R.string.share_body2)} ${detailTvshowBinding.tvShowRateDetail.text}, ${
                    resources.getString(
                        R.string.share_body3
                    )
                }"
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, detailTvshowBinding.tvShowName.text)
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(intent, resources.getString(R.string.share_title)))
        }

    }
}