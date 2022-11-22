package com.shalan.audioplayer.base.fragment

interface IFragment<Model: Any> {

    fun showLoading()

    fun showData(data: Model)

    fun showError(error: String)
}