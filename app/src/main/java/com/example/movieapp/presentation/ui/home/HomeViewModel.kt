package com.example.movieapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.TrendingResponse
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private var _trendingMovies = MutableLiveData<Resource<TrendingResponse>>()
    val trendingMovies: LiveData<Resource<TrendingResponse>> get() = _trendingMovies


    fun getTrendingMovies(
        media_type: String,
        time_window: String,
        api_key: String
    ) {
        _trendingMovies.postValue(Resource.Loading(null, "Loading..."))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _trendingMovies.postValue(repository.getTrendingMovies(media_type, time_window, api_key))
            } catch (e: Exception) {
                e.message
            }
        }
    }

}