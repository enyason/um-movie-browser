package com.android.emovie.ui.movieList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.emovie.R
import com.android.emovie.databinding.FragmentMovieListBinding
import com.android.emovie.databinding.RowItemMovieBinding
import com.android.emovie.ui.Movie
import com.android.emovie.ui.MovieDiffUtil
import com.android.emovie.ui.MovieViewHolder
import com.android.emovie.ui.MovieViewModel
import com.android.emovie.ui.zcustom.RecyclerViewAdapter
import com.android.emovie.ui.zcustom.ViewHolder
import com.android.emovie.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val viewModel: MovieViewModel by navGraphViewModels(R.id.main_nav_graph) {
        defaultViewModelProviderFactory
    }

    private lateinit var binding: FragmentMovieListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.sort_by_popular -> {
                viewModel.sortByPopular()
                true
            }
            R.id.sort_by_favourites -> {
                viewModel.sortByFavourites()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = object : RecyclerViewAdapter<Movie>(MovieDiffUtil()) {
            override fun getLayoutRes(model: Movie): Int {

                return R.layout.row_item_movie
            }

            override fun getViewHolder(
                view: View,
                recyclerViewAdapter: RecyclerViewAdapter<Movie>
            ): ViewHolder<Movie> {
                return MovieViewHolder(RowItemMovieBinding.bind(view)) {

                    val action =
                        MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it)
                    findNavController().navigateSafe(action)


                }
            }

        }


        binding.rvMovieCatalog.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        }


        viewModel.movieList.observe(viewLifecycleOwner, Observer { result ->

            when (result) {

                Result.Loading -> {
                    binding.progressBar.makeVisible()
                }

                is Result.Success -> {
                    binding.progressBar.makeGone().also {
                        movieAdapter.submitList(result.data)
                    }
                }

                is Result.Error -> {
                    binding.progressBar.makeGone()
                    toast(result.error.localizedMessage)
                }
            }


        })
    }

}