package com.shalan.audioplayer.network.respons.model


interface BasePaginatedResponse {

    val count: Long

    val countTotal: Long

    val pages: Long

    fun shouldPaginate(totalLoadedCount: Long): Boolean
}
