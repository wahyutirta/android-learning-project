package com.example.mynetflix.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynetflix.databinding.FragmentMovieBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.vo.Status


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


            viewModel.getMovie().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> binding.progressBarMovie.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBarMovie.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
                        }
                        Status.ERROR -> {
                            binding.progressBarMovie.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun observe(movieAdapter: MovieAdapter){
        
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