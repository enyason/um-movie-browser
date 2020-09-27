package com.android.emovie.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.emovie.local.model.MovieLocal

@Database(
    entities = [MovieLocal::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}