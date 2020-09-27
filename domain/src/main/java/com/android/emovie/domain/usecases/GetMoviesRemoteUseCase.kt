package com.android.emovie.domain.usecases

import com.android.emovie.domain.models.MovieDomain
import com.android.emovie.domain.repositories.MovieRepository
import com.android.emovie.domain.usecases.base.ObservableUseCase
import com.android.emovie.domain.usecases.base.SingleUseCase
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesRemoteUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    SingleUseCase<List<MovieDomain>, GetMoviesRemoteUseCase.Params>() {


    data class Params(
        val sortBy: String
    )
    override fun buildFlowUseCase(params: Params?): Single<List<MovieDomain>> {
        return movieRepository.getMoviesFromRemote(params!!)
    }


}