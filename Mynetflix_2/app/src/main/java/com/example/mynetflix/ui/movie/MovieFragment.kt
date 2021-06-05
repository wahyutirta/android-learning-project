package com.example.mynetflix.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynetflix.databinding.FragmentMovieBinding
import com.example.mynetflix.factory.ViewModelFactory


class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelFactory.getInstance(requireActivity())
            )[MovieVM::class.java]

            val movieAdapter = MovieAdapter()


            onProgress(true)
            viewModel.getMovie().observe(this, { movies ->

                movieAdapter.setMovies(movies)
                movieAdapter.notifyDataSetChanged()
                onProgress(false)
            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun onProgress(state: Boolean) {
        when (state) {
            true -> {
                binding.progressBarMovie.visibility = View.VISIBLE
            }
            false -> {
                binding.progressBarMovie.visibility = View.GONE
            }
        }
    }

}