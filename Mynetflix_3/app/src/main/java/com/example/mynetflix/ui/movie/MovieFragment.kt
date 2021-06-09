package com.example.mynetflix.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynetflix.R
import com.example.mynetflix.databinding.FragmentMovieBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.ui.tvshow.TvShowVM
import com.example.mynetflix.vo.Status
import java.lang.StringBuilder


class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieVM
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProvider(
                this,
                ViewModelFactory.getInstance(requireActivity())
            )[MovieVM::class.java]

            movieAdapter = MovieAdapter()

            observe(viewModel, movieAdapter)


            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun observe(viewModel: MovieVM, movieAdapter: MovieAdapter) {
        viewModel.getMovie().observe(viewLifecycleOwner, { movies ->
            if (movies != null) when (movies.status) {
                Status.LOADING -> onProgress(true)
                Status.SUCCESS -> {
                    onProgress(false)
                    movieAdapter.submitList(movies.data)
                }
                Status.ERROR -> {
                    onProgress(false)
                    val message = StringBuilder(R.string.fail_message)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        })
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