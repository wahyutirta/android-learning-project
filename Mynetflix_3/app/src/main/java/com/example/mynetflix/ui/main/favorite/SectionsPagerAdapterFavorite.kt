package com.example.mynetflix.ui.main.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mynetflix.R
import com.example.mynetflix.ui.main.favorite.favmovie.FavMovieFragment
import com.example.mynetflix.ui.main.favorite.favtvshow.FavTvShowFragment

class SectionsPagerAdapterFavorite(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val tabTitles = intArrayOf(R.string.movie, R.string.tv_Show)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavMovieFragment()
            1 -> FavTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(tabTitles[position])
}