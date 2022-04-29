package com.android.emovie.ui.movieList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.emovie.R
import com.android.emovie.databinding.FragmentMovieListBinding
import com.android.emovie.ui.MovieRecyclerViewAdapter
import com.android.emovie.ui.MovieViewModel
import com.android.emovie.utils.getErrorString
import com.android.emovie.utils.makeGone
import com.android.emovie.utils.makeVisible
import com.android.emovie.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        viewModel.getLatestMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_by_popular -> {
                requireActivity().materialToolbar.title = getString(R.string.popular)
                viewModel.getPopularMovies()
                true
            }
            R.id.sort_by_latest -> {
                requireActivity().materialToolbar.title = getString(R.string.latest)
                viewModel.getLatestMovies()
                true
            }
            R.id.sort_by_upcoming -> {
                requireActivity().materialToolbar.title = getString(R.string.upcoming)
                viewModel.getUpcomingMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMovieListBinding.inflate(inflater).let {
            _binding = it
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieRecyclerViewAdapter {
            val action = MovieListFragmentDirections.toMovieDetails(it)
            findNavController().navigate(action)
        }

        binding.rvMovieCatalog.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        }

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                MovieViewModel.ViewState.Loading -> binding.progressBar.makeVisible()
                is MovieViewModel.ViewState.Content -> {
                    movieAdapter.submitList(viewState.movies)
                    binding.progressBar.makeGone()
                }
                is MovieViewModel.ViewState.Error -> {
                    binding.progressBar.makeGone()
                    toast(getErrorString(viewState.msg))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}