package com.example.mynetflix.ui.main.favorite.favtvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynetflix.databinding.FragmentFavTvShowBinding
import com.example.mynetflix.factory.ViewModelFactory
import com.example.mynetflix.ui.tvshow.TvShowAdapter


class FavTvShowFragment : Fragment() {
    private lateinit var binding: FragmentFavTvShowBinding
    private lateinit var viewModel: FavTvShowFragmentVM
    private lateinit var tvShowAdapter: TvShowAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentFavTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            androidx.lifecycle.ViewModelProvider(
                this,
                ViewModelFactory.getInstance(requireActivity())
            )[FavTvShowFragmentVM::class.java]

        tvShowAdapter = TvShowAdapter()
        observe(viewModel, tvShowAdapter)



        with(binding.rvTvshow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

    }
    private fun observe(viewModel: FavTvShowFragmentVM, tvShowAdapter: TvShowAdapter){
        onProgress(true, binding)
        viewModel.getFavTvShow().observe(viewLifecycleOwner, { tvShow ->
            tvShowAdapter.submitList(tvShow)
            tvShowAdapter.notifyDataSetChanged()
            onProgress(false, binding)
        })
    }
    private fun onProgress(state: Boolean, binding: FragmentFavTvShowBinding) {
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