package com.example.mynetflix.ui.tvshow

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mynetflix.databinding.FragmentTvShowBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.ui.movie.MovieVM


class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelFactory.getInstance(requireActivity())
            )[TvShowVM::class.java]

            val tvShowAdapter = TvShowAdapter()
            onProgress(true)
            viewModel.getTvShow().observe(this, { tvShow ->

                tvShowAdapter.setTvShow(tvShow)
                tvShowAdapter.notifyDataSetChanged()
                onProgress(false)
            })

            with(binding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

        }
    }

    private fun onProgress(state: Boolean) {
        when (state) {
            true -> {
                binding.progressBarTvshow.visibility = View.VISIBLE
            }
            false -> {
                binding.progressBarTvshow.visibility = View.GONE
            }
        }
    }
}