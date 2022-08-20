package com.example.movieapp.data.remote

data class TrendingResponse(
    val page: Int,
    val results: List<Trending>,
    val total_pages: Int,
    val total_results: Int
)