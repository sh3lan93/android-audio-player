package com.shalan.audioplayer.base.usecase

import io.reactivex.rxjava3.core.Single

abstract class BaseSingleUseCase<T : Any, Params : Param> : BaseUseCase<Single<T>, Params>() {

    override fun execute(params: Params): Single<T> = buildUseCase(params)
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
}