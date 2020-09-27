package com.android.emovie.domain.usecases

import com.android.emovie.domain.models.MovieDomain
import com.android.emovie.domain.repositories.MovieRepository
import com.android.emovie.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    ObservableUseCase<List<MovieDomain>, Unit>() {

    override fun buildFlowUseCase(params: Unit?): Observable<List<MovieDomain>> {
        return movieRepository.getMovies()
    }

}