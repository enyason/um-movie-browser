package com.android.emovie.ui

import com.android.emovie.databinding.RowItemMovieBinding
import com.android.emovie.ui.zcustom.ViewHolder
import com.android.emovie.utils.AppConstants.BASE_IMAGE_URL
import com.android.emovie.utils.loadImage

class MovieViewHolder(private val binding: RowItemMovieBinding,private val onMovieClick:(Movie)->Unit) : ViewHolder<Movie>(binding.root) {
    override fun bind(element: Movie) {

        binding.movieImageView.loadImage(BASE_IMAGE_URL + element.poster_path)

        binding.root.setOnClickListener {
            onMovieClick(element)

        }
    }
}