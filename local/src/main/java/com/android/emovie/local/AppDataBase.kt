package com.android.emovie.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.emovie.local.model.LatestMovieLocal
import com.android.emovie.local.model.PopularMovieLocal
import com.android.emovie.local.model.UpcomingMovieLocal

@Database(
    entities = [LatestMovieLocal::class, PopularMovieLocal::class, UpcomingMovieLocal::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}