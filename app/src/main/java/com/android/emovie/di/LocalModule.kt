package com.android.emovie.di

import android.content.Context
import androidx.room.Room
import com.android.emovie.data.sources.LocalDataSource
import com.android.emovie.local.AppDataBase
import com.android.emovie.local.LocalDataSourceImpl
import com.android.emovie.utils.AppConstants.DATABASE_NAME
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalModule {

    @Binds
    @Singleton
    abstract fun localDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}


@Module
@InstallIn(ApplicationComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun dataBase(@ApplicationContext context: Context): AppDataBase {

        return Room.databaseBuilder(
            context,
            AppDataBase::class.java, DATABASE_NAME
        ).build()
    }
}