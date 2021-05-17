package com.example.mynetflix.ui.tvshow

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mynetflix.databinding.FragmentTvShowBinding


class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when {
            activity != null -> {
                val viewModel = ViewModelProvider(
                    this,
                    ViewModelProvider.NewInstanceFactory()
                )[TvShowVM::class.java]

                val tvShow = viewModel.getTvShow()
                val tvShowAdapter = TvShowAdapter()
                tvShowAdapter.setTvShow(tvShow)

                with(fragmentTvShowBinding.rvTvshow) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvShowAdapter
                }

            }
        }
    }

}