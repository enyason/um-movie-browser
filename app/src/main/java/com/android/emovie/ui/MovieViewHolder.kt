package com.android.emovie.ui

import androidx.recyclerview.widget.RecyclerView
import com.android.emovie.databinding.RowItemMovieBinding
import com.android.emovie.utils.AppConstants.BASE_IMAGE_URL
import com.android.emovie.utils.loadImage

class MovieViewHolder(
    private val binding: RowItemMovieBinding,
    private val onMovieClick: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(element: Movie) {
        binding.movieImageView.loadImage(BASE_IMAGE_URL + element.poster_path)

        binding.root.setOnClickListener {
            onMovieClick(element)

        }
    }
}