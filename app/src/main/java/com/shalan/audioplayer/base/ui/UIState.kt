package com.shalan.audioplayer.base.ui

import com.shalan.audioplayer.base.network.BaseApiResponse


sealed class UIState

object Loading : UIState()

class Success<T : Any>(val data: T? = null) : UIState()

class Failure(val error: String?) : UIState()

fun BaseApiResponse.mapToUI(): UIState = when (this) {
    BaseApiResponse.Loading -> Loading
    BaseApiResponse.Completed -> Success<Any>()
    is BaseApiResponse.Success<*> -> Success(this.data)
    is BaseApiResponse.Error<*> -> Failure(this.error.cause)
}

