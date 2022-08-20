package com.example.movieapp.presentation.ui.discover

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.presentation.ui.discover.documentary.DocumentaryFragment
import com.example.movieapp.presentation.ui.discover.movies.MoviesFragment
import com.example.movieapp.presentation.ui.discover.sports.SportsFragment
import com.example.movieapp.presentation.ui.discover.tvseries.TvSeriesFragment
import com.example.movieapp.utils.NUM_TABS


class DiscoverFragmentViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return MoviesFragment()
            1 -> return TvSeriesFragment()
            2 -> return DocumentaryFragment()
        }
        return SportsFragment()
    }
}