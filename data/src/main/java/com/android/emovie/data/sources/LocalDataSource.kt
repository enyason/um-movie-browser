package com.android.emovie.data.sources

import com.android.emovie.data.model.MovieData
import com.android.emovie.domain.usecases.UpdateMovieUseCase
import io.reactivex.Completable
import io.reactivex.Observable

interface LocalDataSource {

    fun updateMovie(params: UpdateMovieUseCase.Params): Completable
    fun getMovies(): Observable<List<MovieData>>
    fun saveMovies(list: List<MovieData>): Completable
}