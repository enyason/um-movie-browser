package com.android.emovie.local

import com.android.emovie.data.model.MovieData
import com.android.emovie.data.sources.LocalDataSource
import com.android.emovie.local.model.LatestMovieLocal
import com.android.emovie.local.model.MovieLocal
import com.android.emovie.local.model.PopularMovieLocal
import com.android.emovie.local.model.UpcomingMovieLocal
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val db: AppDataBase) : LocalDataSource {

    override suspend fun getLatestMovies(): List<MovieData> {
        return db.movieDao().getLatestMovies().map { it.toData() }
    }

    override suspend fun getPopularMovies(): List<MovieData> {
        return db.movieDao().getPopularMovies().map { it.toData() }
    }

    override suspend fun getUpcomingMovies(): List<MovieData> {
        return db.movieDao().getUpcomingMovies().map { it.toData() }
    }

    override suspend fun saveLatestMovies(movies: List<MovieData>) {
        db.movieDao().saveLatestMovies(movies.map { it.toLatest() })
    }

    override suspend fun savePopularMovies(movies: List<MovieData>) {
        db.movieDao().savePopularMovies(movies.map { it.toPopular() })
    }

    override suspend fun saveUpcomingMovies(movies: List<MovieData>) {
        db.movieDao().saveUpcomingMovies(movies.map { it.toUpcoming() })
    }

    private fun LatestMovieLocal.toData(): MovieData {
        return MovieData(
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

    private fun PopularMovieLocal.toData(): MovieData {
        return MovieData(
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


    private fun UpcomingMovieLocal.toData(): MovieData {
        return MovieData(
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

    private fun MovieData.toLatest(): LatestMovieLocal {
        return LatestMovieLocal(
            id,
            adult,
            backdrop_path,
            genre_ids,
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


    private fun MovieData.toPopular(): PopularMovieLocal {
        return PopularMovieLocal(
            id,
            adult,
            backdrop_path,
            genre_ids,
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


    private fun MovieData.toUpcoming(): UpcomingMovieLocal {
        return UpcomingMovieLocal(
            id,
            adult,
            backdrop_path,
            genre_ids,
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