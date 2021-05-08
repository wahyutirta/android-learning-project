@file:Suppress("DEPRECATION")
package com.example.mygithubuserfavorite.ui.view.detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuserfavorite.data.model.ModelUserFollowersItem
import com.example.mygithubuserfavorite.databinding.FragmentFollowerBinding
import com.example.mygithubuserfavorite.network.APIHelper
import com.example.mygithubuserfavorite.network.NetworkAPIClient
import com.example.mygithubuserfavorite.ui.base.ViewModelFactory
import com.example.mygithubuserfavorite.ui.view.detail.DetailUserActivity
import com.example.mygithubuserfavorite.ui.viewAdapter.ItemFollowersAdapter
import com.example.mygithubuserfavorite.ui.viewModel.MainViewModel
import com.example.mygithubuserfavorite.utils.Status

class FollowerFragment : Fragment() {

    private lateinit var itemAdapter: ItemFollowersAdapter
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
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
            rvFollowers.layoutManager = LinearLayoutManager(context)
            itemAdapter = ItemFollowersAdapter(arrayListOf())
            rvFollowers.adapter = itemAdapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(APIHelper(NetworkAPIClient.apiService)))
            .get(MainViewModel::class.java)
    }

    private fun setUpObserver(query: String) {
        viewModel.getFollowerList(query).observe(viewLifecycleOwner, {
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
                    progressFragmentFollower.visibility = View.VISIBLE
                    rvFollowers.visibility = View.GONE
                }
            }
            2 -> { //on success
                binding.apply {
                    progressFragmentFollower.visibility = View.GONE
                    rvFollowers.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun retrieve(followersItem: List<ModelUserFollowersItem>) {
        setUI()
        itemAdapter.apply {
            addUsers(followersItem)
            notifyDataSetChanged()

        }
    }


}