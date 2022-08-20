package com.example.movieapp.presentation.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.databinding.FragmentDiscoverBinding
import com.example.movieapp.utils.viewPagerItem
import com.google.android.material.tabs.TabLayoutMediator

class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DiscoverViewModel::class.java)

        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DiscoverFragmentViewPagerAdapter(childFragmentManager, lifecycle)
        binding.apply {
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) {tab, position ->
                tab.text = viewPagerItem[position]
            }.attach()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}