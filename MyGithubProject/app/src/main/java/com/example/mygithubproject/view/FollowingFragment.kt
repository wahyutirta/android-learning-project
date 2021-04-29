package com.example.mygithubproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubproject.R
import com.example.mygithubproject.model.UserFollowingModel
import com.example.mygithubproject.model.UserModel
import com.example.mygithubproject.viewmodel.UserFollowingAdapter
import com.example.mygithubproject.viewmodel.ViewModelFollowing
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {

    companion object {
        val TAG = FollowingFragment::class.java.simpleName
        const val EXTRA_DETAIL = "extra_detail"
    }

    private var listUser: ArrayList<UserFollowingModel> = ArrayList()
    private lateinit var adapter: UserFollowingAdapter
    private lateinit var viewModelFollowing: ViewModelFollowing
    private var bool: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserFollowingAdapter(listUser)
        viewModelFollowing = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(ViewModelFollowing::class.java)

        val userFollowing = activity!!.intent.getParcelableExtra<UserModel>(EXTRA_DETAIL) as UserModel
        config()

        viewModelFollowing.loadApiGitFollowing(
            activity!!.applicationContext,
            userFollowing.username.toString()
        )
        showLoading(true)

        viewModelFollowing.loadListFollowing().observe(activity!!, Observer { listDataUser ->
            if (listDataUser != null) {
                adapter.loadData(listDataUser)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBarFollowing.visibility = View.VISIBLE
        } else {
            progressBarFollowing.visibility = View.INVISIBLE
        }
    }

    private fun config() {
        recyclerViewFollowing.layoutManager = LinearLayoutManager(activity)
        recyclerViewFollowing.setHasFixedSize(true)
        recyclerViewFollowing.adapter = adapter
    }

}