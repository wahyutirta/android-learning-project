package com.example.mynetflix.ui.tvshow

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynetflix.R

import com.example.mynetflix.databinding.FragmentTvShowBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.ui.movie.MovieAdapter
import com.example.mynetflix.ui.movie.MovieVM
import com.example.mynetflix.vo.Status
import java.lang.StringBuilder


class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowVM
    private lateinit var tvShowAdapter: TvShowAdapter

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
            viewModel = ViewModelProvider(
                this,
                ViewModelFactory.getTvShowInstance(requireActivity())
            )[TvShowVM::class.java]

            tvShowAdapter = TvShowAdapter()

            observe(viewModel, tvShowAdapter, binding)


            with(binding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

        }
    }

    private fun observe(viewModel: TvShowVM, tvShowAdapter: TvShowAdapter, binding: FragmentTvShowBinding) {
        viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
            if (tvShow != null) when (tvShow.status) {
                Status.LOADING -> {
                    onProgress(true, binding)
                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    onProgress(false, binding)
                    tvShowAdapter.submitList(tvShow.data)
                    tvShowAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    onProgress(false, binding)
                    val message = StringBuilder(R.string.fail_message)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun onProgress(state: Boolean, binding: FragmentTvShowBinding) {
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