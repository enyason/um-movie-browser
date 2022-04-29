package com.android.emovie.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.emovie.databinding.FragmentMovieDetailBinding
import com.android.emovie.utils.AppConstants.BASE_IMAGE_URL
import com.android.emovie.utils.loadImage
import kotlinx.android.synthetic.main.activity_main.*

class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val args = MovieDetailFragmentArgs.fromBundle(requireArguments())

        requireActivity().materialToolbar.title = args.movie.title

        return FragmentMovieDetailBinding.inflate(inflater).apply {
            with(args.movie) {
                movieTitle.text = title
                movieRating.text = vote_average.toString()
                movieDescription.text = overview
                movieReleaseDate.text = release_date
                movieImage.loadImage(BASE_IMAGE_URL + backdrop_path)
            }
        }.root
    }
}