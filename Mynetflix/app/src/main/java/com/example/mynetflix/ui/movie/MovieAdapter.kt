package com.example.mynetflix.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ItemsMovieBinding
import com.example.mynetflix.model.MovieModel
import com.example.mynetflix.ui.detail.DetailMovieActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<MovieModel>()

    class MovieViewHolder(private val itemsMovieBinding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(itemsMovieBinding.root) {
        fun bind(movie: MovieModel) {
            with(itemsMovieBinding) {
                movieName.text = movie.title
                movieRelease.text = movie.releaseDate
                movieRate.text = movie.movieRate
                Glide.with(itemView.context)
                    .load(movie.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loader)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.id)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

    fun setMovies(movies: List<MovieModel>?) {
        when (movies) {
            null -> return
            else -> {
                this.listMovie.clear()
                this.listMovie.addAll(movies)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = listMovie[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovie.size
}