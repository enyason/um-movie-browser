package com.android.emovie.data.repositories

import com.android.emovie.data.model.MovieData
import com.android.emovie.data.sources.DataSourceFactory
import com.android.emovie.domain.models.MovieDomain
import com.android.emovie.domain.repositories.MovieRepository
import com.android.emovie.domain.usecases.GetMoviesRemoteUseCase
import com.android.emovie.domain.usecases.GetMoviesUseCase
import com.android.emovie.domain.usecases.UpdateMovieUseCase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dataSourceFactory: DataSourceFactory
) : MovieRepository {

    override fun getMoviesFromRemote(params: GetMoviesRemoteUseCase.Params): Single<List<MovieDomain>> {
        val remote = dataSourceFactory.remote().getMovies(params).doOnSuccess {
            /**
             * save to db
             */
            dataSourceFactory.local().saveMovies(it).subscribe()
        }

        return remote.map {
            it.map { movieData ->
                with(movieData) {
                    MovieDomain(
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
        }
    }

    override fun getMovies(): Observable<List<MovieDomain>> {


        val local = dataSourceFactory.local().getMovies()


        return local.map {
            it.map { movieData ->
                with(movieData) {
                    MovieDomain(
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
        }
    }


    override fun updateMovie(params: UpdateMovieUseCase.Params): Completable {

        return dataSourceFactory.local().updateMovie(params)
    }

}