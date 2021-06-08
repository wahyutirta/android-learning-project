package com.example.mynetflix.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ItemsMovieBinding
import com.example.mynetflix.model.data.MovieModel
import com.example.mynetflix.ui.detail.DetailMovieActivity

class MovieAdapter : PagedListAdapter<MovieModel, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    class MovieViewHolder(private val itemsBinding: ItemsMovieBinding) :
            RecyclerView.ViewHolder(itemsBinding.root) {

        fun bind(movie: MovieModel) {

            with(itemsBinding) {
                Glide.with(itemView.context)
                        .load(movie.imagePath)
                        .placeholder(R.drawable.ic_loader)
                        .error(R.drawable.ic_error)
                        .into(poster)
                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate
                movieRatings.text = movie.ratings
                movieDirectors.text = movie.filmDirector
                itemListener(itemView, movie)

            }
        }

        private fun itemListener(itemView: View, movie: MovieModel) {
            itemView.setOnClickListener {
                val message = "${movie.title} Tapped"
                Toast.makeText(itemView.context, message, Toast.LENGTH_SHORT).show()

                val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIESELECTED, movie.id)
                itemView.context.startActivity(intent)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding =
                ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

}