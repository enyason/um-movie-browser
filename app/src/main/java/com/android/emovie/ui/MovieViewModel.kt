package com.android.emovie.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.emovie.domain.usecases.GetMoviesRemoteUseCase
import com.android.emovie.domain.usecases.GetMoviesUseCase
import com.android.emovie.domain.usecases.UpdateMovieUseCase
import com.android.emovie.utils.Result
import com.android.emovie.utils.asLiveData
import com.android.emovie.utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel @ViewModelInject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase,
    private val getMoviesRemoteUseCase: GetMoviesRemoteUseCase
) :
    ViewModel() {


    private val _movieList = MutableLiveData<Result<List<Movie>>>()
    val movieList = _movieList.asLiveData()

    private val _updateMovie = MutableLiveData<Event<Result<Nothing>>>()
    val updateMovie = _updateMovie.asLiveData()

    private var allMovies = mutableListOf<Movie>()

    private var sortedByFav = false


    private val container = CompositeDisposable()

    init {
        getMovies()
        getMoviesRemote()
    }

    fun sortByFavourites() {

        viewModelScope.launch(Dispatchers.IO) {
            sortedByFav = true
            val sorted = allMovies.filter { it.isFavorite }

            if (sorted.isNotEmpty()) {
                _movieList.postValue(Result.Success(sorted))
            } else {
                _movieList.postValue(Result.Error(Throwable("You have no movies added to favourite")))

            }
        }
    }

    fun sortByPopular() {

        viewModelScope.launch(Dispatchers.IO) {
            sortedByFav = false
            _movieList.postValue(Result.Success(allMovies))
        }
    }


    private fun getMoviesRemote() {
        val params = GetMoviesRemoteUseCase.Params(sortBy = "popularity.desc")

        val disposable = getMoviesRemoteUseCase
            .execute(params)
            .doOnSubscribe {
                _movieList.postValue(Result.Loading)
            }
            .subscribe({

                if (sortedByFav) {
                    sortByFavourites()
                } else {
                    sortByPopular()
                }
            }, {
                _movieList.postValue(Result.Error(it))
            }
            )

        container.add(disposable)
    }

    private fun getMovies() {


        val disposable = getMoviesUseCase
            .execute()
            .doOnSubscribe {
                _movieList.postValue(Result.Loading)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val movies = it.map { movie ->
                        with(movie) {
                            Movie(
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
                                vote_count,
                                isFavourite
                            )
                        }
                    }

                    allMovies = movies.toMutableList()

                    if (sortedByFav) {
                        sortByFavourites()
                    } else {
                        sortByPopular()
                    }
                }, {
                    _movieList.postValue(Result.Error(it))

                }
            )

        container.add(disposable)
    }

    fun updateDb(params: UpdateMovieUseCase.Params) {

        val disposable = updateMovieUseCase
            .execute(params).subscribe({
                _updateMovie.postValue(Event(Result.Success()))
            }, {
                _updateMovie.postValue(Event(Result.Error(it)))
            })
        container.add(disposable)
    }

}