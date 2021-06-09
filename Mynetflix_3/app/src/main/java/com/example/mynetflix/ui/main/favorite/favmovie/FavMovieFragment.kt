package com.example.mynetflix.ui.main.favorite.favmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynetflix.databinding.FragmentFavMovieBinding
import com.example.mynetflix.databinding.FragmentFavTvShowBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.ui.main.favorite.favtvshow.FavTvShowFragmentVM
import com.example.mynetflix.ui.movie.MovieAdapter
import com.example.mynetflix.ui.tvshow.TvShowAdapter

class FavMovieFragment : Fragment() {

    private lateinit var binding: FragmentFavMovieBinding
    private lateinit var viewModel: FavMovieFragmentVM
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentFavMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(
                this,
                ViewModelFactory.getInstance(requireActivity())
            )[FavMovieFragmentVM::class.java]

        movieAdapter = MovieAdapter()
        observe(viewModel, movieAdapter)


        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun observe(viewModel: FavMovieFragmentVM, movieAdapter: MovieAdapter){
        onProgress(true, binding)
        viewModel.getFavMovies().observe(viewLifecycleOwner, { movies ->
            movieAdapter.submitList(movies)
            movieAdapter.notifyDataSetChanged()
            onProgress(false, binding)
        })
    }

    private fun onProgress(state: Boolean, binding: FragmentFavMovieBinding) {
        when (state) {
            true -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            false -> {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

}