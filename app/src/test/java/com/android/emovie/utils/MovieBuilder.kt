package com.android.emovie.utils

import com.android.emovie.data.model.MovieData
import com.android.emovie.domain.models.MovieDomain
import com.android.emovie.ui.Movie


object Movie {

    fun buildDomainModels(): List<MovieDomain> {
        return buildList {
            add(
                MovieDomain(
                    false,
                    "",
                    emptyList(),
                    (0..1000).random(),
                    "language",
                    "title",
                    "overview",
                    0.0,
                    "poster_path",
                    "release_date",
                    "title",
                    true,
                    0.0,
                    4
                )
            )


            add(
                MovieDomain(
                    false,
                    "",
                    emptyList(),
                    (0..1000).random(),
                    "language",
                    "title",
                    "overview",
                    0.0,
                    "poster_path",
                    "release_date",
                    "title",
                    true,
                    0.0,
                    4
                )
            )

        }

    }


    fun buildDataModels(): List<MovieData> {
        return listOf(
            MovieData(
                false,
                "",
                emptyList(),
                (0..1000).random(),
                "language",
                "title",
                "overview",
                0.0,
                "poster_path",
                "release_date",
                "title",
                true,
                0.0,
                4
            ),

            MovieData(
                false,
                "",
                emptyList(),
                (0..1000).random(),
                "language",
                "title",
                "overview",
                0.0,
                "poster_path",
                "release_date",
                "title",
                true,
                0.0,
                4
            )


        )

    }
}


fun MovieDomain.toUIModel(): Movie {
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