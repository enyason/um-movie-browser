package com.android.emovie.domain

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val cause: Throwable) : Result<Nothing>()
}
