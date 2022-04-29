package com.android.emovie.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.emovie.R
import com.android.emovie.domain.models.MovieDomain
import com.android.emovie.domain.usecases.*
import com.android.emovie.utils.Dispatcher
import com.android.emovie.utils.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieViewModel @ViewModelInject constructor(
    private val getLatestMovies: GetLatestMovies,
    private val getPopularMovies: GetPopularMovies,
    private val getUpcomingMovies: GetUpcomingMovies,
    private val dispatcher: Dispatcher
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState = _viewState.asLiveData()

    fun getLatestMovies() = viewModelScope.launch(dispatcher.io()) {
        getLatestMovies
            .execute()
            .flowOn(dispatcher.io())
            .onStart { _viewState.postValue(ViewState.Loading) }
            .catch {
                _viewState.postValue(ViewState.Error(it.localizedMessage ?: R.string.error_message))
            }
            .collect { movies ->
                val uiItems = movies.map { it.toUIModel() }
                _viewState.postValue(ViewState.Content(uiItems))
            }
    }


    fun getPopularMovies() = viewModelScope.launch(dispatcher.io()) {
        getPopularMovies
            .execute()
            .flowOn(dispatcher.io())
            .onStart { _viewState.postValue(ViewState.Loading) }
            .catch {
                _viewState.postValue(ViewState.Error(it.localizedMessage ?: R.string.error_message))
            }
            .collect { movies ->
                val uiItems = movies.map { it.toUIModel() }
                _viewState.postValue(ViewState.Content(uiItems))
            }
    }

    fun getUpcomingMovies() = viewModelScope.launch(dispatcher.io()) {
        getUpcomingMovies
            .execute()
            .flowOn(dispatcher.io())
            .onStart { _viewState.postValue(ViewState.Loading) }
            .catch {
                _viewState.postValue(ViewState.Error(it.localizedMessage ?: R.string.error_message))
            }
            .collect { movies ->
                val uiItems = movies.map { it.toUIModel() }
                _viewState.postValue(ViewState.Content(uiItems))
            }
    }


    sealed class ViewState {
        object Loading : ViewState()
        data class Content(val movies: List<Movie>) : ViewState()
        data class Error(val msg: Any) : ViewState()
    }


    companion object {

        private fun MovieDomain.toUIModel(): Movie {
            return Movie(
                adult,
                backdrop_path,
                genre_ids,
                id,
                original_language,
                original_title,
                overview,
                popularity,
                poster_path,
                release_date,
                title,
                video,
                vote_average,
                vote_count
            )
        }

    }
}