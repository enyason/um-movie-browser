package com.android.emovie.local

import androidx.room.*
import com.android.emovie.local.model.MovieLocal
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovies(movie: List<MovieLocal>): Completable


    @Query("UPDATE movie_table SET isFavourite=:isFav WHERE id = :movieId")
    fun updateMovie(movieId: Int, isFav: Boolean): Completable

    @Query("SELECT * FROM movie_table")
    fun getMovies(): Observable<List<MovieLocal>>
}