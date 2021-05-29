package com.example.mynetflix.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynetflix.R
import com.example.mynetflix.databinding.ItemsMovieBinding
import com.example.mynetflix.model.MovieModel
import com.example.mynetflix.ui.detail.DetailMovieActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<MovieModel>()

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
                movieRatings.text = movie.movieRate

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIESELECTED, movie.id)
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

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MovieViewHolder{
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