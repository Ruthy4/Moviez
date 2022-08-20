package com.example.movieapp.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.utils.Resource
import com.example.movieapp.utils.api_key
import com.example.movieapp.utils.media_type
import com.example.movieapp.utils.time_window
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    lateinit var trendingAdapter: HomeRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getTrendingMovies(media_type, time_window, BuildConfig.API_KEY)
        trendingAdapter = HomeRvAdapter {
        }
        observeResponse()
    }

    private fun observeResponse() {
        homeViewModel.trendingMovies.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    binding.trendingRv.adapter = trendingAdapter
                    trendingAdapter.submitList(it.value.results)
                }
                is Resource.Error -> {
                    Snackbar.make(binding.trendingRv, it.error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                is Resource.Loading -> {
                    Snackbar.make(binding.trendingRv, "Loading", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}