package com.shalan.audioplayer.base.usecase

abstract class DefaultUseCaseWithReturnType<Params : Param, Return : Any> {

    abstract fun execute(params: Params): Return
}