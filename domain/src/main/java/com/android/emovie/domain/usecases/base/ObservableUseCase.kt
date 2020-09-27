package com.android.emovie.domain.usecases.base

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<Result, in Param> {

    protected abstract fun buildFlowUseCase(params: Param? = null): Observable<Result>

    fun execute(params: Param? = null): Observable<Result> {
        return buildFlowUseCase(params).subscribeOn(Schedulers.io())
    }
}