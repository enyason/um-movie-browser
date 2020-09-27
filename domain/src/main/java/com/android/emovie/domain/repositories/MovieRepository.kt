package com.android.emovie.domain.repositories

import com.android.emovie.domain.models.MovieDomain
import com.android.emovie.domain.usecases.GetMoviesRemoteUseCase
import com.android.emovie.domain.usecases.GetMoviesUseCase
import com.android.emovie.domain.usecases.UpdateMovieUseCase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRepository {
    fun getMovies():Observable<List<MovieDomain>>
    fun getMoviesFromRemote(params: GetMoviesRemoteUseCase.Params):Single<List<MovieDomain>>
    fun updateMovie(params: UpdateMovieUseCase.Params): Completable
}