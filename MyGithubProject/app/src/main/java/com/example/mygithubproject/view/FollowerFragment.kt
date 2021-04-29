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
import com.example.mygithubproject.model.UserFollowerModel
import com.example.mygithubproject.model.UserModel
import com.example.mygithubproject.viewmodel.UserFollowerAdapter
import com.example.mygithubproject.viewmodel.ViewModelFollower
import kotlinx.android.synthetic.main.fragment_follower.*

class FollowerFragment : Fragment() {

    companion object {
        val TAG = FollowerFragment::class.java.simpleName
        const val EXTRA_DETAIL = "extra_detail"
    }

    private val followerList: ArrayList<UserFollowerModel> = ArrayList()
    private lateinit var adapter: UserFollowerAdapter
    private lateinit var viewModelFollower: ViewModelFollower

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follower, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserFollowerAdapter(followerList)
        viewModelFollower = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        ).get(ViewModelFollower::class.java)
        val userFollower = activity!!.intent.getParcelableExtra<UserModel>(EXTRA_DETAIL) as UserModel
        config()

        viewModelFollower.loadApiGitFollower(activity!!.applicationContext, userFollower.username.toString())
        showLoading(true)

        viewModelFollower.loadListFollowers().observe(activity!!, Observer { listDataUser ->
            if (listDataUser != null) {
                adapter.loadData(listDataUser)
                showLoading(false)
            }
        })
    }

    private fun config() {
        recyclerViewFollower.layoutManager = LinearLayoutManager(activity)
        recyclerViewFollower.setHasFixedSize(true)
        recyclerViewFollower.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBarFollower.visibility = View.VISIBLE
        } else {
            progressBarFollower.visibility = View.INVISIBLE
        }
    }
}