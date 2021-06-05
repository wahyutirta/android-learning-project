package com.example.mynetflix.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ItemsTvshowBinding
import com.example.mynetflix.model.data.TvShowModel
import com.example.mynetflix.ui.detail.DetailTvShowActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShow = ArrayList<TvShowModel>()

    class TvShowViewHolder(private val itemsBinding: ItemsTvshowBinding) :
            RecyclerView.ViewHolder(itemsBinding.root) {

        fun bind(tvShow: TvShowModel) {
            with(itemsBinding) {

                Glide.with(itemView.context)
                        .load(tvShow.imagePath)
                        .placeholder(R.drawable.ic_loader)
                        .error(R.drawable.ic_error)
                        .into(poster)
                tvShowTitle.text = tvShow.title
                tvShowRelease.text = tvShow.releaseDate
                tvShowRate.text = tvShow.ratings
                tvShowCreators.text = tvShow.creators

                itemListener(itemView, tvShow)

            }
        }

        private fun itemListener(itemView: View, tvShow: TvShowModel) {
            itemView.setOnClickListener {
                val message = "${tvShow.title} Tapped"
                Toast.makeText(itemView.context, message, Toast.LENGTH_SHORT).show()

                val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                intent.putExtra(DetailTvShowActivity.EXTRA_TVSELECTED, tvShow.id)
                itemView.context.startActivity(intent)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvShowBinding =
                ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvShowBinding)
    }
    override fun getItemCount(): Int = listTvShow.size
    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

}