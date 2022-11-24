package com.shalan.audioplayer.base.usecase

abstract class DefaultUseCase<Params: Param> {

    abstract fun execute(params: Params)
}