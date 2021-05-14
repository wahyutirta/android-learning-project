package com.example.mygithubproject.view.userdetail

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubproject.R
import com.example.mygithubproject.databinding.FragmentFollowBinding
import com.example.mygithubproject.viewmodel.UserAdapter
import com.example.mygithubproject.viewmodel.ViewModelFollower

class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private lateinit var VMFollower: ViewModelFollower
    private lateinit var adapter: UserAdapter
    private lateinit var username: String
    private var bindingFragment: FragmentFollowBinding? = null
    private val binding get() = bindingFragment!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(UserDetailActivity.EXTRA_USERNAME).toString()
        bindingFragment = FragmentFollowBinding.bind(view)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }

        showLoading(true)
        VMFollower = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ViewModelFollower::class.java)
        VMFollower.setList(username)

        VMFollower.getList().observe(viewLifecycleOwner) {
            adapter.setList(it)
            showLoading(false)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingFragment = null
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}