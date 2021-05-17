package com.example.mynetflix.ui.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mynetflix.R
import com.example.mynetflix.ui.movie.MovieFragment
import com.example.mynetflix.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    companion object {
        @StringRes
        private val tabTitles = intArrayOf(R.string.movie, R.string.tv_Show)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when {
            position == 0 -> MovieFragment()
            position == 1 -> TvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(tabTitles[position])

}