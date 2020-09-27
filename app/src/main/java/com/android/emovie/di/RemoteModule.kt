package com.android.emovie.di

import com.android.emovie.data.sources.RemoteDataSource
import com.android.emovie.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoteModule {

    @Binds
    @Singleton
    abstract fun remoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}