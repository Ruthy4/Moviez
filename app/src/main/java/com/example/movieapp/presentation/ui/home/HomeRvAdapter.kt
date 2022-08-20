package com.example.movieapp.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.remote.Trending
import com.example.movieapp.databinding.TrendingVpItemBinding
import com.example.movieapp.utils.poster_url

class HomeRvAdapter(private val onItemClick: (Trending) -> Unit) :
    ListAdapter<Trending, HomeRvAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        val binding: TrendingVpItemBinding,
        private val onItemClick: (Trending) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(trending: Trending) {
            binding.apply {
                trendingMovieTitleTv.text = trending.title
                ratingTv.text = trending.vote_average.toString()
                Glide.with(binding.root.context)
                    .load(poster_url+trending.backdrop_path)
                    .placeholder(R.drawable.trending_pic)
                    .into(posterIv)

                trendingRootLayout.setOnClickListener {
                    onItemClick.invoke(trending)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TrendingVpItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DiffCallback: DiffUtil.ItemCallback<Trending>() {
    override fun areItemsTheSame(oldItem: Trending, newItem: Trending): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Trending, newItem: Trending): Boolean {
        return oldItem == newItem
    }

}
