package com.shalan.audioplayer.base.usecase

import io.reactivex.rxjava3.core.Scheduler

abstract class BaseUseCase<UseCaseType : Any, ParamsType : Param> {

    abstract val ioScheduler: Scheduler
    abstract val mainScheduler: Scheduler

    abstract fun buildUseCase(params: ParamsType): UseCaseType

    abstract fun execute(params: ParamsType): UseCaseType
}