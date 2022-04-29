package com.android.emovie.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
class TestDispatcher : Dispatcher {
    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()
    override fun mainThread(): CoroutineDispatcher = UnconfinedTestDispatcher()
}