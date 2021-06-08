package com.example.mynetflix.ui.tvshow

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mynetflix.databinding.FragmentTvShowBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.ui.movie.MovieAdapter
import com.example.mynetflix.ui.movie.MovieVM
import com.example.mynetflix.vo.Status


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
                ViewModelFactory.getInstance(requireActivity())
            )[TvShowVM::class.java]

            tvShowAdapter = TvShowAdapter()
            onProgress(true)
            observe(tvShowAdapter)
            viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null) {
                    when(tvShow.status) {
                        Status.LOADING -> binding.progressBarTvshow.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBarTvshow.visibility = View.GONE
                            tvShowAdapter.submitList(tvShow.data)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding.progressBarTvshow.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

        }
    }
    private fun observe(tvShowAdapter: TvShowAdapter){
        
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