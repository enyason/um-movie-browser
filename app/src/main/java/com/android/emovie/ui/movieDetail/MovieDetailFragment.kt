package com.android.emovie.ui.movieDetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.android.emovie.R
import com.android.emovie.databinding.FragmentMovieDetailBinding
import com.android.emovie.domain.usecases.UpdateMovieUseCase
import com.android.emovie.ui.MovieViewModel
import com.android.emovie.utils.AppConstants.BASE_IMAGE_URL
import com.android.emovie.utils.Result
import com.android.emovie.utils.loadImage
import com.android.emovie.utils.toast
import com.android.emovie.utils.EventObserver
import kotlinx.android.synthetic.main.activity_main.*

class MovieDetailFragment : Fragment() {


    lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()

    private val viewModel: MovieViewModel by navGraphViewModels(R.id.main_nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /**
         * set toolbar title to movie name
         */
        requireActivity().materialToolbar.title = args.movie.title
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(args.movie) {
            binding.movieTitle.text = title
            binding.movieRating.text = vote_average.toString()
            binding.movieDescription.text = overview
            binding.movieReleaseDate.text = release_date
            binding.movieImage.loadImage(BASE_IMAGE_URL + backdrop_path)

            binding.favouritesButton.isFavorite = isFavorite
        }

        binding.favouritesButton.setOnFavoriteChangeListener { _, favorite ->
            val param = UpdateMovieUseCase.Params(args.movie.id, favorite)
            viewModel.updateDb(param)
        }


        viewModel.updateMovie.observe(viewLifecycleOwner, EventObserver { result ->
            when (result) {

                Result.Loading -> {
                }

                is Result.Success -> {
                    if (binding.favouritesButton.isFavorite) {
                        /**
                         * added to fav
                         */
                        /**
                         * added to fav
                         */
                        toast("Added ${args.movie.title} to favourites")
                    } else {

                        /**
                         * removed from
                         */

                        /**
                         * removed from
                         */
                        toast("Removed ${args.movie.title} from favourites")

                    }

                }

                is Result.Error -> {
                    toast(result.error.localizedMessage)
                }
            }
        })
    }

}