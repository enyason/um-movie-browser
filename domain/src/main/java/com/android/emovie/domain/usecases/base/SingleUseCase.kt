package com.android.emovie.domain.usecases.base

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Result, in Param> {

    protected abstract fun buildFlowUseCase(params: Param? = null): Single<Result>

    fun execute(params: Param? = null): Single<Result> {
        return buildFlowUseCase(params).subscribeOn(Schedulers.io())
    }
}