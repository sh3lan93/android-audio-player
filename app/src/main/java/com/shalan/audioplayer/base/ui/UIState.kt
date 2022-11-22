package com.shalan.audioplayer.base.ui


sealed class UIState<T : Any>(val data: T? = null, val error: String? = null)

class Loading<T : Any> : UIState<T>()

class Success<T : Any>(data: T? = null) : UIState<T>(data = data)

class Failure<T : Any>(error: String?) : UIState<T>(error = error)

