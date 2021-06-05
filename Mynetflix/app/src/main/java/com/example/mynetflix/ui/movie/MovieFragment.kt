package com.example.mynetflix.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynetflix.databinding.FragmentMovieBinding


class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when {
            activity != null -> {
                val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieVM::class.java]
                val movies = viewModel.getMovie()

                val movieAdapter = MovieAdapter()
                movieAdapter.setMovies(movies)

                with(binding.rvMovie) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            }
        }
    }

}