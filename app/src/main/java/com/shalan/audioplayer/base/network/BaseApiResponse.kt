package com.shalan.audioplayer.base.network

sealed class BaseApiResponse {

    object Loading : BaseApiResponse()

    object Completed : BaseApiResponse()

    data class Success<T : Any>(val data: T) : BaseApiResponse()

    data class Error<T : NetworkError>(val error: T) : BaseApiResponse()
}