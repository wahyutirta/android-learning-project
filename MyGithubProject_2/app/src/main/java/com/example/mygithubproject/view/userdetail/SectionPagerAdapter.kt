package com.example.mygithubproject.view.userdetail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mygithubproject.R

class SectionPagerAdapter(private val context: Context, fragmentMg: FragmentManager, data: Bundle) :
    FragmentPagerAdapter(fragmentMg, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundle: Bundle = data

    @StringRes
    private val tabTitles = intArrayOf(R.string.follower, R.string.following)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }

        fragment?.arguments = this.fragmentBundle

        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(tabTitles[position])
    }

}