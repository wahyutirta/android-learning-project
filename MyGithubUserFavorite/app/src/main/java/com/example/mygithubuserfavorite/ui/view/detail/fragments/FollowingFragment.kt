@file:Suppress("DEPRECATION")
package com.example.mygithubuserfavorite.ui.view.detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuserfavorite.data.model.ModelUserFollowingItem
import com.example.mygithubuserfavorite.databinding.FragmentFollowingBinding
import com.example.mygithubuserfavorite.network.APIHelper
import com.example.mygithubuserfavorite.network.NetworkAPIClient
import com.example.mygithubuserfavorite.ui.base.ViewModelFactory
import com.example.mygithubuserfavorite.ui.view.detail.DetailUserActivity
import com.example.mygithubuserfavorite.ui.viewAdapter.ItemFollowingAdapter
import com.example.mygithubuserfavorite.ui.viewModel.MainViewModel
import com.example.mygithubuserfavorite.utils.Status

class FollowingFragment : Fragment() {
    private lateinit var itemAdapter: ItemFollowingAdapter
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginKey = arguments?.getString(DetailUserActivity.LOGIN_KEY).toString()
        setupViewModel()
        setUpObserver(loginKey)
    }

    private fun setUI() {
        binding.apply {
            rvFollowing.layoutManager = LinearLayoutManager(context)
            itemAdapter = ItemFollowingAdapter(arrayListOf())
            rvFollowing.adapter = itemAdapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(APIHelper(NetworkAPIClient.apiService)))
            .get(MainViewModel::class.java)
    }

    private fun setUpObserver(query: String) {
        viewModel.getFollowingList(query).observe(viewLifecycleOwner,Observer {
            it?.let { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        showProgress(2)
                        resources.data?.let { userFollowers -> retrieve(userFollowers) }
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                        showProgress(1)
                    }
                }
            }

        })
    }

    private fun showProgress(status: Int) {
        when (status) {
            1 -> { // On progress
                binding.apply {
                    progressFragmentFollowing.visibility = View.VISIBLE
                    rvFollowing.visibility = View.GONE
                }
            }
            2 -> { //on success
                binding.apply {
                    progressFragmentFollowing.visibility = View.GONE
                    rvFollowing.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun retrieve(followersItem: List<ModelUserFollowingItem>) {
        setUI()
        itemAdapter.apply {
            addUsers(followersItem)
            notifyDataSetChanged()

        }
    }


}