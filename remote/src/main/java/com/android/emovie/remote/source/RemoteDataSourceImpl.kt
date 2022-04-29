package com.android.emovie.remote.source

import com.android.emovie.data.model.MovieData
import com.android.emovie.data.sources.RemoteDataSource
import com.android.emovie.remote.api.MovieApi
import com.android.emovie.remote.models.MovieRemote
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val api: MovieApi) : RemoteDataSource {

    override suspend fun getLatestMovies(): List<MovieData> {
        return api.getLatestMovies().results.map {
            it.toDataModel()
        }
    }

    override suspend fun getPopularMovies(): List<MovieData> {
        return api.getPopularMovies().results.map {
            it.toDataModel()
        }
    }

    override suspend fun getUpcomingMovies(): List<MovieData> {
        return api.getUpcomingMovies().results.map {
            it.toDataModel()
        }
    }


    fun MovieRemote.toDataModel(): MovieData {
        return MovieData(
            adult,
            backdrop_path.orEmpty(),
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