package com.example.mynetflix.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ItemsTvshowBinding
import com.example.mynetflix.model.TvShowModel
import com.example.mynetflix.ui.detail.DetailTvShowActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShow = ArrayList<TvShowModel>()

    class TvShowViewHolder(private val itemsTvShowBinding: ItemsTvshowBinding) :
        RecyclerView.ViewHolder(itemsTvShowBinding.root) {

        fun bind(tvShow: TvShowModel) {
            with(itemsTvShowBinding) {
                tvShowName.text = tvShow.title
                tvShowRelease.text = tvShow.releaseDate
                tvShowRate.text = tvShow.ratings
                Glide.with(itemView.context)
                    .load(tvShow.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loader)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV, tvShow.id)
                    itemView.context.startActivity(intent)
                }

            }
        }

    }

    fun setTvShow(tvShow: List<TvShowModel>?) {
        when (tvShow) {
            null -> return
            else -> {
                this.listTvShow.clear()
                this.listTvShow.addAll(tvShow)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowViewHolder {
        val itemsTvShowBinding =
            ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

}