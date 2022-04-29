package com.android.emovie.utils

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

interface Dispatcher {
    fun io(): CoroutineDispatcher
    fun mainThread(): CoroutineDispatcher
}

class CoroutineDispatcherImpl @Inject constructor() : Dispatcher {
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun mainThread(): CoroutineDispatcher = Dispatchers.Main
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class DispatcherModule{

    @Binds
    @Singleton
    abstract fun provideDispatcher(dispatcher: CoroutineDispatcherImpl) : Dispatcher
}